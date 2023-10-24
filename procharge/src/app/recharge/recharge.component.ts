import { Component, OnInit } from '@angular/core';
import { NewSimService } from '../services/new-sim.service';
import { NetworkOperator } from '../models/NetworkOperator';
import { FetchPlans } from '../models/FetchPlans';
import { Router } from '@angular/router';
import { RechargeSimService } from '../services/recharge-sim.service';
import { RechargePlans } from '../models/RechargePlans';

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
  rechargePlans: RechargePlans[] = [];
  fetchPlans: FetchPlans = { mobileNumber: '', operator: '', operatorCircle: '', categoryName: 'Data' };

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
    this.fetchPlans.mobileNumber = this.EnteredmobileNumber;
    this.fetchPlans.operator = this.selectedOperator;
    this.fetchPlans.operatorCircle = this.selectedOperatorCircle;

    this.rechargeSimService.fetchPlanDetails(this.fetchPlans).subscribe(
      (rechargePlans: RechargePlans[]) => {
        this.rechargePlans = rechargePlans;
        this.rechargeSimService.cachePlanDetails(rechargePlans);    
        this.router.navigate(['/recharge-plans']);
      });
  }
}