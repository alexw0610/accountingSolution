import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Income } from '../classes/income';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class IncomeService {
	
	
	constructor(private http: HttpClient) { }
  
	public getAll(): Observable<Income[]> {
		return this.http.get<Income[]>("http://localhost:8080/listIncome");
	}
	
	public add(income: Income):Observable<any> {
		const headers = { 'content-type': 'application/json'};
		const body=JSON.stringify(income);
		return this.http.post("http://localhost:8080/addIncome",body,{'headers':headers});
	}
	
	public remove(income: Income):Observable<any> {
		const headers = { 'content-type': 'application/json'};
		const body=JSON.stringify(income);
		return this.http.post("http://localhost:8080/deleteIncome",body,{'headers':headers});

	}

	
}
