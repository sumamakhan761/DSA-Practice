// https://leetcode.com/problems/design-task-manager/description/

import java.util.*;

class TaskManager {
    private class Task implements Comparable<Task> {
        int userId;
        int taskId;
        int priority;

        Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }

        @Override
        public int compareTo(Task other) {
            if (this.priority != other.priority) {
                return Integer.compare(other.priority, this.priority); // Higher priority first
            }
            if (this.taskId != other.taskId) {
                return Integer.compare(other.taskId, this.taskId); // Higher taskId first
            }
            return Integer.compare(this.userId, other.userId); // Tie-breaker
        }
    }

    private TreeSet<Task> taskSet;
    private HashMap<Integer, Task> taskMap;

    public TaskManager(List<List<Integer>> tasks) {
        // Custom comparator is defined in Task's compareTo method
        taskSet = new TreeSet<>();
        taskMap = new HashMap<>();

        for (List<Integer> taskInfo : tasks) {
            int userId = taskInfo.get(0);
            int taskId = taskInfo.get(1);
            int priority = taskInfo.get(2);
            Task task = new Task(userId, taskId, priority);
            taskSet.add(task);
            taskMap.put(taskId, task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        taskSet.add(task);
        taskMap.put(taskId, task);
    }

    public void edit(int taskId, int newPriority) {
        Task oldTask = taskMap.get(taskId);
        if (oldTask != null) {
            taskSet.remove(oldTask); // O(log n)
            Task newTask = new Task(oldTask.userId, taskId, newPriority);
            taskSet.add(newTask); // O(log n)
            taskMap.put(taskId, newTask);
        }
    }

    public void rmv(int taskId) {
        Task task = taskMap.get(taskId);
        if (task != null) {
            taskSet.remove(task); // O(log n)
            taskMap.remove(taskId); // O(1)
        }
    }

    public int execTop() {
        if (taskSet.isEmpty()) {
            return -1;
        }
        Task topTask = taskSet.first();
        taskSet.remove(topTask);
        taskMap.remove(topTask.taskId);
        return topTask.userId;
    }
}