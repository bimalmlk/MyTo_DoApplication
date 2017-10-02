package bimal.todo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bimal.todo.model.Todo;
import bimal.todo.service.TodoService;
/*
 * This controller class can manage any types of operations for managing toDos 
 * 
 */
@Controller
public class TodoController {

	@Autowired
	private TodoService service;

	/*
	 * This method can be used for providing date format for Application.
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	/*
	 * show list of default toDos.
	 */
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodosList(ModelMap model) {
		String user = getLoggedInUserName();
		model.addAttribute("todos", service.retrieveTodos(user));
		return "list-todos";
	}
/*
 * this method render the page to todo page new toDo.
 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo());
		return "todo";
	}
/*
 * this method will make the post operation for adding todos 
 * and will show the newly ADDED todo in the list of todo page.
 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors())
			return "todo";

		service.addTodo(getLoggedInUserName(), todo.getDesc(), new Date(),
				todo.getTargetDate(), false);
		model.clear();
		return "redirect:/list-todos";
	}
/*
 * this method gives the details about user who logged in.
 */
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
/*
 * This method will render the page for edit page for todo update.
 */
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(ModelMap model, @RequestParam int id) {
		model.addAttribute("todo", service.retrieveTodo(id));
		return "todo";
	}
/*
 * This method will update the todo and make the post operation and show the todo in list of todos.
 */
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo,
			BindingResult result) {
		if (result.hasErrors())
			return "todo";

		todo.setUser(getLoggedInUserName());
		todo.setStartDate(service.retrieveTodo(todo.getId()).getStartDate());
		service.updateTodo(todo);

		model.clear();
		return "redirect:/list-todos";
	}
/*
 * This method will delete the todo and remove the todo from list.
 */
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);

		return "redirect:/list-todos";
	}
}
