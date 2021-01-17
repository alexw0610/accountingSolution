import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Expense } from '../classes/expense';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ExpenseService {

	constructor(private http: HttpClient) { }
  
	public getAll(): Observable<Expense[]> {
		return this.http.get<Expense[]>("http://localhost:8080/listExpenses");
	}
	
	public add(expense: Expense): Observable<any> {
		const headers = { 'content-type': 'application/json'};
		const body=JSON.stringify(expense);
		return this.http.post("http://localhost:8080/addExpense",body,{'headers':headers});
	}
	
	public remove(expense: Expense): Observable<any> {
		const headers = { 'content-type': 'application/json'};
		const body=JSON.stringify(expense);
		return this.http.post("http://localhost:8080/deleteExpense",body,{'headers':headers});

	}


}
