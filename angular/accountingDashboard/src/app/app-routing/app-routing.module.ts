import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router

import { HomeComponent } from '../home/home.component';
import { IncomeFormComponent } from '../income-form/income-form.component';
import { ExpenseFormComponent } from '../expense-form/expense-form.component';

const routes: Routes = [
  { path: '', children: [] },
  { path: 'addIncome', component: IncomeFormComponent },
  { path: 'addExpense', component: ExpenseFormComponent }
];
// configures NgModule imports and exports
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }