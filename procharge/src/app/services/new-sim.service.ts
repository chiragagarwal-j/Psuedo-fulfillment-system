import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'; // Import Observable if not already imported
import { NewSim } from '../models/NewSim';
import { NetworkOperator } from '../models/NetworkOperator';

@Injectable({
  providedIn: 'root'
})
export class NewSimService {
  baseUrl = 'http://localhost:8080/ordersim';

  constructor(private http: HttpClient) { }

  getOperator(): Observable<NetworkOperator[]> {
    const url = `${this.baseUrl}/getOperator`;
    return this.http.get<NetworkOperator[]>(url);
  }

  orderNewSim(id: number, newSimDto: NewSim): Observable<string> {
    const url = `${this.baseUrl}/newSim/${id}`;
    return this.http.post<string>(url, newSimDto);
  }

}
