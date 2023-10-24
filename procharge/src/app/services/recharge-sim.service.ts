import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RechargePlans } from '../models/RechargePlans';
import { Observable } from 'rxjs';
import { FetchPlans } from '../models/FetchPlans';
import { RechargeInfo } from '../models/RechargeInfo';

@Injectable({
  providedIn: 'root'
})
export class RechargeSimService {

  cachedInfo: RechargeInfo= { mobileNumber: '', operator: '', operatorCircle: '', planID: 1 };;

  baseUrl = 'http://localhost:8080/rechargesim';

  constructor(private http: HttpClient) { }

  fetchPlanDetails(fetchPlans: FetchPlans): Observable<RechargePlans[]> {
    const url = `${this.baseUrl}/getPlans`;
    return this.http.post<RechargePlans[]>(url, fetchPlans);
  }

  cachePlanDetails(toBeStored: RechargeInfo) {
    this.cachedInfo = toBeStored;
  }

  retrieveCachedPlans() {
    return this.cachedInfo;
  }

}