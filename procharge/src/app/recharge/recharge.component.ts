import { Component, OnInit } from '@angular/core';
import { NewSimService } from '../services/new-sim.service';
import { NetworkOperator } from '../models/NetworkOperator';
import { FetchPlans } from '../models/FetchPlans';
import { Router } from '@angular/router';
import { RechargeSimService } from '../services/recharge-sim.service';
import { RechargePlans } from '../models/RechargePlans';
import { RechargeInfo } from '../models/RechargeInfo';

@Component({
  selector: 'app-recharge',
  templateUrl: './recharge.component.html',
  styleUrls: ['./recharge.component.css']
})

export class RechargeComponent implements OnInit {
  EnteredmobileNumber: string = '';
  selectedOperatorCircle: string = '';
  selectedOperator: string = '';
  uniqueOperators: string[] = [];
  availableOperatorCircle: string[] = [];
  availableOperators: NetworkOperator[] = [];
  rechargeinfo: RechargeInfo = { mobileNumber: '', operator: '', operatorCircle: '', planID: 1, payVia: '', payingInfo: '', amount: '' };

  constructor(private newSimService: NewSimService, private router: Router, private rechargeSimService: RechargeSimService) { }

  ngOnInit() {
    this.getUniqueOperators();
  }

  getUniqueOperators() {
    this.newSimService.getOperator().subscribe((operators: NetworkOperator[]) => {
      this.availableOperators = operators;
      this.uniqueOperators = Array.from(new Set(operators.map(operator => operator.operator)));
    });
  }

  onOperatorChange() {
    if (this.selectedOperator) {
      this.availableOperatorCircle = this.availableOperators
        .filter(operator => operator.operator === this.selectedOperator)
        .map(operator => operator.operatorCircle);
    } else {
      this.availableOperatorCircle = [];
    }
  }

  submitRecharge() {
    this.rechargeinfo.mobileNumber = this.EnteredmobileNumber;
    this.rechargeinfo.operator = this.selectedOperator;
    this.rechargeinfo.operatorCircle = this.selectedOperatorCircle;
    this.rechargeSimService.cachePlanDetails(this.rechargeinfo);
    this.router.navigate(['/recharge-plans', this.selectedOperator]);
  }
}