import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RechargePlans } from '../models/RechargePlans';
import { Observable } from 'rxjs';
import { FetchPlans } from '../models/FetchPlans';
import { RechargeInfo } from '../models/RechargeInfo';
import { RechargeOrderStatus } from '../models/RechargeOrderStatus';
import { FetchOrderID } from '../models/FetchOrderID';

@Injectable({
  providedIn: 'root'
})
export class RechargeSimService {

  cachedInfo: RechargeInfo = { mobileNumber: '', operator: '', operatorCircle: '', planID: 1, payVia: '', paymentInfo: '', amount: '', orderID: '' };
  cachedOrderID: string = '';
  baseUrl = 'http://localhost:8080/rechargesim';

  constructor(private http: HttpClient) { }

  fetchPlanDetails(fetchPlans: FetchPlans): Observable<RechargePlans[]> {
    const url = `${this.baseUrl}/getPlans`;
    return this.http.post<RechargePlans[]>(url, fetchPlans);
  }

  getOrderID(): Observable<FetchOrderID> {
    const url = `${this.baseUrl}/createOrderID`;
    return this.http.get<FetchOrderID>(url);
  }

  cachePlanDetails(toBeStored: RechargeInfo) {
    this.cachedInfo = toBeStored;
  }

  retrieveCachedPlans() {
    return this.cachedInfo;
  }

  processRecharge(rechargeInfoToSend: RechargeInfo): Observable<string> {
    const url = `${this.baseUrl}/processRecharge`;
    return this.http.post<string>(url, rechargeInfoToSend);
  }

  getRechargeOrderDetails(): Observable<RechargeOrderStatus> {
    const url = `${this.baseUrl}/getRechargeOrderDetails/${this.cachedOrderID}`;
    console.log(this.cachedOrderID);
    return this.http.get<RechargeOrderStatus>(url);
  }  

  retriveOrderId() {
    return this.cachedOrderID;
  }

  setOrderId(orderID: string): void {
    this.cachedOrderID = orderID;
  }

}