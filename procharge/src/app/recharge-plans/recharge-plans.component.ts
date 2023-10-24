import { Component, OnInit } from '@angular/core';
import { RechargePlans } from '../models/RechargePlans';
import { RechargeSimService } from '../services/recharge-sim.service';

@Component({
  selector: 'app-recharge-plans',
  templateUrl: './recharge-plans.component.html',
  styleUrls: ['./recharge-plans.component.css']
})
export class RechargePlansComponent implements OnInit {
  receivedData: RechargePlans[] = [];

  constructor(private rechargeSimService: RechargeSimService) { }
  ngOnInit() {
    this.receivedData = this.rechargeSimService.retrieveCachedPlans()??[];
  }

  toggleDetails(plan: RechargePlans) {
    plan.showDetails = !plan.showDetails;
  }

}
