import { Component, OnInit } from '@angular/core';
import { RechargePlans } from '../models/RechargePlans';
import { RechargeSimService } from '../services/recharge-sim.service';
import { OperatorPlans } from '../models/OperatorPlans';
import { RechargeInfo } from '../models/RechargeInfo';
import { FetchPlans } from '../models/FetchPlans';
import { Router } from '@angular/router';

const operatorPlans: { [key: string]: OperatorPlans } = {
  Airtel: {
    name: 'Airtel',
    services: [
      { name: 'Combo', type: 'Combo' },
      { name: 'Data', type: 'Data' },
      { name: 'Talktime', type: 'Talktime' },
      { name: 'Truly Unlimited', type: 'Truly_unlimited' },
      { name: 'Entertainment', type: 'Entertainment' },
      { name: 'International Roaming', type: 'International_roaming' },
      { name: 'Annual', type: 'Annual' }
    ]
  },

  BSNL: {
    name: 'BSNL Mobile',
    services: [
      { name: 'Combo', type: 'Combo' },
      { name: 'Data', type: 'Data' },
      { name: 'Talktime', type: 'Talktime' },
      { name: 'Truly Unlimited', type: 'Truly_unlimited' },
      { name: 'SMS', type: 'SMS' },
      { name: 'Enterprise', type: 'Enterprise' },
      { name: 'Entertainment', type: 'Entertainment' },
      { name: 'Special Offer', type: 'Special_offer' },
      { name: 'International Roaming', type: 'International_roaming' },
      { name: 'Annual', type: 'Annual' }
    ]
  },

  Jio: {
    name: 'Jio',
    services: [
      { name: 'Combo', type: 'Combo' },
      { name: 'Data', type: 'Data' },
      { name: 'Talktime', type: 'Talktime' },
      { name: 'Jio Phone', type: 'Jio_phone' },
      { name: 'Truly Unlimited', type: 'Truly_unlimited' },
      { name: 'Entertainment', type: 'Entertainment' },
      { name: 'International Roaming', type: 'International_roaming' },
      { name: 'Annual', type: 'Annual' }
    ]
  },

  MTNL: {
    name: 'MTNL',
    services: [
      { name: 'Combo', type: 'Combo' },
      { name: 'Data', type: 'Data' },
      { name: 'SMS', type: 'SMS' },
      { name: 'Special Offer', type: 'Special_offer' },
      { name: 'Talktime', type: 'Talktime' },
      { name: 'STD', type: 'STD' },
    ]
  },
};

@Component({
  selector: 'app-recharge-plans',
  templateUrl: './recharge-plans.component.html',
  styleUrls: ['./recharge-plans.component.css']
})

export class RechargePlansComponent implements OnInit {
  rechargeInfo: RechargeInfo = { mobileNumber: '', operator: '', operatorCircle: '', planID: 1, payVia: '', paymentInfo: '', amount: '', orderID: '' };
  operatorName: string | null = null;
  selectedOperatorPlans: OperatorPlans | null = null;
  rechargePlans: RechargePlans[] = [];
  fetchPlans: FetchPlans = { operator: '', categoryName: 'Combo' };
  selectedCategoryName: string = '';

  constructor(private rechargeSimService: RechargeSimService, private router: Router) { }

  ngOnInit() {
    this.rechargeInfo = this.rechargeSimService.retrieveCachedPlans();
    this.operatorName = this.rechargeInfo.operator;
    this.selectedOperatorPlans = this.operatorName ? operatorPlans[this.operatorName] : null;
    this.fetchCategoryPlans();
  }

  selectedCategory(selectedCategoryType: string, categoryName: string) {
    this.fetchPlans.categoryName = selectedCategoryType;
    this.selectedCategoryName = categoryName;
    this.fetchCategoryPlans();
  }

  fetchCategoryPlans() {
    this.fetchPlans.operator = this.rechargeInfo.operator;
    this.rechargeSimService.fetchPlanDetails(this.fetchPlans).subscribe(
      (rechargePlans: RechargePlans[]) => {
        this.rechargePlans = rechargePlans;
      });
  }

  toggleDetails(plan: RechargePlans) {
    plan.showDetails = !plan.showDetails;
  }

  sendRechargeRequest(planID: number, price: string) {
    this.rechargeInfo.planID = planID;
    this.rechargeInfo.amount = price;
    this.rechargeSimService.cachePlanDetails(this.rechargeInfo);
    this.router.navigate(['/payment-page']);
  }

}