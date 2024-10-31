package report;

import com.google.gson.*;
import report.model.Employee;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class EmployeeJsonSerializer implements JsonSerializer {

    public static String name = "name";
    public static String hired = "hired";
    public static String fired = "fired";
    public static String salary = "salary";

    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        Employee employee = (Employee) o;
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(name, new JsonPrimitive(employee.getName()));
        jsonObject.add(hired, new JsonPrimitive(sdf.format(employee.getHired().getTime())));
        jsonObject.add(fired, new JsonPrimitive(sdf.format(employee.getFired().getTime())));
        jsonObject.add(salary, new JsonPrimitive(employee.getSalary()));
        return jsonObject;
    }
}
