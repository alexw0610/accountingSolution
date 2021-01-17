import { Component, OnInit , Output, EventEmitter} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';

import { Income } from '../classes/income'
import { IncomeService } from '../service/income.service';

@Component({
  selector: 'app-income-form',
  templateUrl: './income-form.component.html',
  styleUrls: ['./income-form.component.css']
})
export class IncomeFormComponent implements OnInit {
	
	@Output() addValue = new EventEmitter();

	income: Income;
	
	checkoutForm = this.formBuilder.group({
		amount: '',
		reoccurring: ''
	});
  
	constructor(private route: ActivatedRoute, 
      private router: Router, 
	  private incomeService: IncomeService,
	  private formBuilder: FormBuilder
	  ) { }
	
	onSubmit() {
		this.income = new Income;
		this.income.value = this.checkoutForm.value.amount;
		this.income.reoccurring = this.checkoutForm.value.reoccurring;
		this.income.date = new Date().toISOString().split('T')[0];
		console.log(this.income);
		this.incomeService.add(this.income).subscribe(data => {
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
