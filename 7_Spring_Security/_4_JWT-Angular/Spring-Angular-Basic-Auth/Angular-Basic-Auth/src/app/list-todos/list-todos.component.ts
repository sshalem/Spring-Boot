import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Todo } from '../common/todo';
import { TodoDataService } from '../service/data/todo-data.service';

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css'],
})
export class ListTodosComponent implements OnInit {
  todos: Todo[] = [];
  message: string = '';

  constructor(
    private todoDataService: TodoDataService,
    private router: Router
  ) {}

  ngOnInit() {
    this.refreshTodos();
  }

  refreshTodos() {
    this.todoDataService.retrieveAllTodos(`sshb`).subscribe(
      (response) => {
        console.log(response);
        this.todos = response;
      },
      (error) => {}
    );
  }

  deleteTodo(id: any) {
    this.todoDataService.deleteTodo('sshb', id).subscribe(
      (res) => {
        console.log(res);
        this.message = `Delete ${id} Successful`;
        this.refreshTodos();
      },
      (err) => {
        console.log(err);
      }
    );
  }

  updateTodo(id: any) {
    this.router.navigate([`todos`, id]);
  }

  addTodo() {
    this.router.navigate([`todos`, -1]);
  }
}
