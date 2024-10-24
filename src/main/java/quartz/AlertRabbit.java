package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    public static int getInterval() {
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            return Integer.parseInt(config.getProperty("rabbit.interval"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static Connection initConnection() {
        try (InputStream input = AlertRabbit.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(input);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("db.url"),
                    config.getProperty("db.username"),
                    config.getProperty("db.password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {
        int interval = getInterval();
        try (Connection conn = initConnection()) {
            List<Connection> connection = new ArrayList<>();
            connection.add(conn);
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            JobDataMap data = new JobDataMap();
            data.put("connection", connection);

            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();

            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval)
                    .repeatForever();

            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();

            scheduler.scheduleJob(job, trigger);

            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        private void saveTime(Connection connection, LocalDateTime datetime) {
            try (PreparedStatement statement =
                         connection.prepareStatement("INSERT INTO rabbit(created_date) VALUES (?)")) {
                statement.setTimestamp(1, Timestamp.valueOf(datetime));
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            List<Connection> conn = (List<Connection>) context.getJobDetail().getJobDataMap().get("connection");
            saveTime(conn.get(0), LocalDateTime.now().withNano(0));
        }
    }
}