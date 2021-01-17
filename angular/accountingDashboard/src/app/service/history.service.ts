import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Datapoint } from '../classes/datapoint';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class HistoryService {

  constructor(private http: HttpClient) { }
  
	public getHistoryDelta(): Observable<Datapoint[]> {
		return this.http.get<Datapoint[]>("http://localhost:8080/monthlyHistory");
	}
	
	public getHistoryCumulative(): Observable<Datapoint[]> {
		return this.http.get<Datapoint[]>("http://localhost:8080/monthlyHistoryCumulative");
	}
}
