package bimal.todo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import bimal.todo.model.Todo;
/*
 * This serice class can provide all todo operations to controller.
 */
@Service
public class TodoService {

	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 3;
	
	/*
	 * This static block will added todos to a list  and will show to UI at first login.
	 */
		static{
			todos.add(new Todo(1, "bimal", "Todo Application with No Data Base",
					new Date(), new Date(), true));
			todos.add(new Todo(2, "bimal", "Todo Application with delete, Edit Button", new Date(), new Date(), true));
			todos.add(new Todo(3, "admin", "Todo Application with Database", new Date(), new Date(), false));
		}
		/*
		 * This method can retrieve all static descriptions and all newly added description fromthe list.
		 */
		public List<Todo> retrieveTodos(String user) {
			List<Todo> filteredTodos = new ArrayList<Todo>();
			for (Todo todo : todos) {
				if (todo.getUser().equals(user))
					filteredTodos.add(todo);
			}
			return filteredTodos;
		}
		/*
		 * This method can return a single todo by Id as an argument.
		 */
	public Todo retrieveTodo(int id){
		for (Todo todo : todos) {
			if (todo.getId() == id)
				return todo;
		}
		return null;
	}
	/*
	 * This method can update an existing todo.
	 */
	public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}
	/*
	 * This method will add a new todo.
	 */
	public void addTodo(String name, String desc,Date starttDate, Date targetDate, boolean isDone) {
		todos.add(new Todo(++todoCount, name, desc, starttDate, targetDate, isDone));
	}
	/*
	 * This method can delete a todo from list by passing id as an argument.
	 */
	public void deleteTodo(int id) {
		Iterator<Todo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			Todo todo = iterator.next();
			if (todo.getId() == id) {
				iterator.remove();
			}
		}
	}
}
