package algo;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {
    private Node root;

    public boolean put(T key, V value) {
        if (root == null) {
            root = new Node(key, value);
            return true;
        }
        return insert(root, key, value);
    }

    private boolean insert(Node node, T key, V value) {
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(key, value);
                balance(node);
                return true;
            }
            return insert(node.left, key, value);
        } else if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(key, value);
                balance(node);
                return true;
            }
            return insert(node.right, key, value);
        } else {
            node.value = value;
            return false;
        }
    }

    public boolean remove(T key) {
        if (root == null) {
            return false;
        }
        RootWrapper rootWrapper = new RootWrapper();
        rootWrapper.node = root;
        boolean removed = remove(rootWrapper, key);
        root = rootWrapper.node;
        return removed;
    }

    private boolean remove(RootWrapper rootWrapper, T key) {
        Node node = rootWrapper.node;
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (remove(new RootWrapper(node.left), key)) {
                node.left = rootWrapper.node;
                balance(node);
                return true;
            }
        } else if (cmp > 0) {
            if (remove(new RootWrapper(node.right), key)) {
                node.right = rootWrapper.node;
                balance(node);
                return true;
            }
        } else {
            if (node.left == null) {
                rootWrapper.node = node.right;
            } else if (node.right == null) {
                rootWrapper.node = node.left;
            } else {
                Node minNode = findMin(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                remove(new RootWrapper(node.right), minNode.key);
            }
            return true;
        }
        return false;
    }

    private Node findMin(Node node) {
        return node.left == null ? node : findMin(node.left);
    }

    public V get(T key) {
        return get(root, key);
    }

    private V get(Node node, T key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public Set<T> keySet() {
        Set<T> keys = new HashSet<>();
        collectKeys(root, keys);
        return keys;
    }

    private void collectKeys(Node node, Set<T> keys) {
        if (node != null) {
            keys.add(node.key);
            collectKeys(node.left, keys);
            collectKeys(node.right, keys);
        }
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        collectValues(root, values);
        return values;
    }

    private void collectValues(Node node, List<V> values) {
        if (node != null) {
            values.add(node.value);
            collectValues(node.left, values);
            collectValues(node.right, values);
        }
    }

    private void balance(Node node) {
        updateHeight(node);
        node.balanceFactor = height(node.left) - height(node.right);
        if (node.balanceFactor > 1) {
            if (node.left.balanceFactor < 0) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (node.balanceFactor < -1) {
            if (node.right.balanceFactor > 0) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
    }

    private void updateHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private class RootWrapper {
        Node node;

        RootWrapper() {

        }

        RootWrapper(Node node) {
            this.node = node;
        }
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }
}
