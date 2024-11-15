package com.example.model

object TaskRepository {

    val tasks = mutableListOf(
        Task("cleaning", "Clean the house", Priority.LOW),
        Task("gardening", "Mow the lawn", Priority.MEDIUM),
        Task("shopping", "Buy the groceries", Priority.HIGH),
        Task("painting", "Paint the fence", Priority.MEDIUM)

    )

    fun allTasks(): List<Task> = tasks

    fun tasksByPriority(priority: Priority) = tasks.filter { it.priority == priority }


    fun taskByName(name: String) = tasks.find {
        it.name.equals(name, ignoreCase = true)
    }

    fun addTask(task: Task) {
        if (taskByName(task.name) != null) {
            throw IllegalArgumentException("Duplicate detected!")
        }
        tasks.add(task)

    }
}