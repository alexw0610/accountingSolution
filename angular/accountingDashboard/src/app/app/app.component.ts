import { Component, OnInit , Output, EventEmitter} from '@angular/core';

import { Income } from '../classes/income';
import { Expense } from '../classes/expense';
import { ActivatedRoute, Router } from '@angular/router';

import { ExpenseService } from '../service/expense.service';
import { IncomeService } from '../service/income.service';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

	income: Income[];
	expense: Expense[];

	@Output() shareDataEvent = new EventEmitter();

		
	constructor(private incomeService: IncomeService, 
				private expenseService: ExpenseService,
				private route: ActivatedRoute, 
				private router: Router 
				) {
	}
	
	deleteIncome(income){
		this.incomeService.remove(income).subscribe(data => {
			this.ngOnInit();
			this.updateData();
			});
	}
	
	deleteExpense(expense){
		this.expenseService.remove(expense).subscribe(data => {
			this.ngOnInit();
			this.updateData();
			});

	}
	
	updateData() {
        this.shareDataEvent.emit("update");
    }

	addIncome(){
		this.router.navigate(['addIncome']);
	}
	addExpense(){
		this.router.navigate(['addExpense']);
	}
	
	updateListData(data: any[]){
		this.ngOnInit();
	}
	
	ngOnInit() {
		
		this.expenseService.getAll().subscribe(data => {
			this.expense = data;
		});
		
		this.incomeService.getAll().subscribe(data => {
			this.income = data;
		});
		
		
	}
}
