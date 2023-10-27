import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'; // Import Observable if not already imported
import { NewSim } from '../models/NewSim';
import { NetworkOperator } from '../models/NetworkOperator';
import { FetchOrderID } from '../models/FetchOrderID';
import { NewSimOrderStatus } from '../models/NewSimOrderStatus';

@Injectable({
  providedIn: 'root'
})
export class NewSimService {
  cachedOrderID: string = '';

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

  getOrderID(): Observable<FetchOrderID> {
    const url = `${this.baseUrl}/createOrderID`;
    return this.http.get<FetchOrderID>(url);
  }

  fetchOrderDetails(): Observable<NewSimOrderStatus> {
    console.log(this.cachedOrderID);
    
    const url = `${this.baseUrl}/getOrderDetails?orderID=${this.cachedOrderID}`;    
    return this.http.get<NewSimOrderStatus>(url);
  }

  // fetchOrderDetails(orderID: string): Observable<NewSimOrderStatus> {
  //   const url = `${this.baseUrl}/getRechargeOrderDetails?orderID=${orderID}`;
  //   return this.http.get<NewSimOrderStatus>(url);
  // }

  retriveOrderId() {
    return this.cachedOrderID;
  }

  setOrderId(orderID: string): void {
    this.cachedOrderID = orderID;
  }

}
