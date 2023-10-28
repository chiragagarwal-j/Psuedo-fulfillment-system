import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'; // Import Observable if not already imported
import { NewSim } from '../models/NewSim';
import { NetworkOperator } from '../models/NetworkOperator';
import { FetchOrderID } from '../models/FetchOrderID';
import { NewSimOrderStatus } from '../models/NewSimOrderStatus';
import { FetchMobileNumber } from '../models/FetchMobileNumber';

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
    const url = `${this.baseUrl}/getOrderDetails?orderID=${this.cachedOrderID}`;
    return this.http.get<NewSimOrderStatus>(url);
  }

  sendOTPtoMobile(orderID: string): Observable<FetchMobileNumber> {
    const url = `${this.baseUrl}/getOTPNewSim?orderID=${orderID}`;
    return this.http.get<FetchMobileNumber>(url);
  }

  validateOTPandFetchInfo(orderID: string, inputOtp: string, mobileNumber: string): Observable<any> {
    const url = `${this.baseUrl}/validateOTP?orderID=${orderID}&inputOtp=${inputOtp}&mobileNumber=${mobileNumber}`;
    return this.http.post<any>(url, null);
  }

  retriveOrderId() {
    return this.cachedOrderID;
  }

  setOrderId(orderID: string): void {
    this.cachedOrderID = orderID;
  }

}
