import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Chart } from 'angular-highcharts';

import { Datapoint } from '../classes/datapoint';
import { HistoryService } from '../service/history.service';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
styleUrls: ['./graph.component.css']
})

export class GraphComponent implements OnInit {
	
	title = 'Dashboard';
	deltaDataPoints;
	cumulativeDataPoints;
	chart: Chart;

	init() {
		

		this.chart = new Chart({
				chart: {
				  type: 'line'
				},

				title: {
				  text: 'Account'
				},
				credits: {
				  enabled: false
				},
				xAxis: {
					opposite: false,
					categories: Object.keys(this.deltaDataPoints)
				},
				series: [{type: 'line', name: 'Total', data:Object.values(this.cumulativeDataPoints)},{type: 'line', name: 'Delta',data:Object.values(this.deltaDataPoints)}]
				
			});
		
	}
	
		
	constructor(private historyService: HistoryService) { }
  
	updateGraphData(data: any[]) {
		this.ngOnInit();
	}
		
	ngOnInit() {
		  
		Observable.forkJoin([
			this.historyService.getHistoryDelta(),
			this.historyService.getHistoryCumulative()]
		).subscribe(result => {
			this.deltaDataPoints = result[0];
			this.cumulativeDataPoints = result[1];
			this.init();
		});
			

	}

}
