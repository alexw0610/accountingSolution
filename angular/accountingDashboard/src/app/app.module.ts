import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app/app.component';
import { GraphComponent } from './graph/graph.component';
import { HomeComponent } from './home/home.component';
import { ExpenseFormComponent } from './expense-form/expense-form.component';
import { IncomeFormComponent } from './income-form/income-form.component';

import { ChartModule } from 'angular-highcharts';

import { ExpenseService } from './service/expense.service';
import { IncomeService } from './service/income.service';
import { HistoryService } from './service/history.service';

import { BoolYesNoPipe } from './pipe/bool-yes-no.pipe';


@NgModule({
  declarations: [
    AppComponent,
    BoolYesNoPipe,
    GraphComponent,
    HomeComponent,
	ExpenseFormComponent,
	IncomeFormComponent
  ],
  imports: [
    BrowserModule,
	HttpClientModule,
	ChartModule,
	FormsModule,
    ReactiveFormsModule,
	AppRoutingModule 
  ],
  providers: [ExpenseService,IncomeService,HistoryService],
  bootstrap: [HomeComponent]
})

export class AppModule { }

