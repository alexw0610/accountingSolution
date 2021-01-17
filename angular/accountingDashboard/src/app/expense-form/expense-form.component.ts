import { Component, OnInit , Output, EventEmitter} from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';

import { Expense } from '../classes/expense'
import { ExpenseService } from '../service/expense.service';

@Component({
  selector: 'app-expense-form',
  templateUrl: './expense-form.component.html',
  styleUrls: ['./expense-form.component.css']
})
export class ExpenseFormComponent implements OnInit {

	@Output() addValue = new EventEmitter();

	expense: Expense;
	
	checkoutForm = this.formBuilder.group({
		amount: '',
		reoccurring: ''
	});
  
	constructor(private route: ActivatedRoute, 
      private router: Router, 
	  private expenseService: ExpenseService,
	  private formBuilder: FormBuilder
	  ) { }
	
	onSubmit() {
		this.expense = new Expense;
		this.expense.value = this.checkoutForm.value.amount;
		this.expense.reoccurring = this.checkoutForm.value.reoccurring;
		this.expense.date = new Date().toISOString().split('T')[0];
		console.log(this.expense);
		this.expenseService.add(this.expense).subscribe(data => {
			this.updateData()
			this.closeAddingForm();
			})
	}
	
	updateData() {
        this.addValue.emit("update");
    }
	
	closeAddingForm(){
		this.router.navigate(['']);
	}
	
	cancel(){
		this.router.navigate(['']);
	}
	
	ngOnInit() {
		
	}

}
