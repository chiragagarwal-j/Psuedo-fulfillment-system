import { Component, OnInit } from '@angular/core';
import { NewSim } from '../models/NewSim';
import { NetworkOperator } from '../models/NetworkOperator';
import { NewSimService } from '../services/new-sim.service';

@Component({
  selector: 'app-get-new-sim',
  templateUrl: './get-new-sim.component.html',
  styleUrls: ['./get-new-sim.component.css']
})
export class GetNewSimComponent implements OnInit {
  newSim: NewSim = {
    firstName: '',
    lastName: '',
    email: '',
    addressLine1: '',
    addressLine2: '',
    city: '',
    state: '',
    pincode: '',
    aadhaarCard: '',
    type: ''
  };
  selectedBrand: string | null = null;
  selectedOperator: NetworkOperator | null = null;
  uniqueBrands: string[] = [];
  availableOperators: NetworkOperator[] = [];

  constructor(private newSimService: NewSimService) {}

  ngOnInit() {
    this.getOperators();
  }

  getOperators() {
    this.newSimService.getOperator().subscribe((operators: NetworkOperator[]) => {
      this.uniqueBrands = Array.from(new Set(operators.map(operator => operator.brand)));
      this.availableOperators = operators;
    });
  }

  filterOperatorsByBrand() {
    if (this.selectedBrand) {
      this.availableOperators = this.availableOperators.filter(
        operator => operator.brand === this.selectedBrand
      );
      this.selectedOperator = null;
    } else {
      this.availableOperators = this.availableOperators;
    }
  }

  orderNewSim() {
    if (this.selectedOperator) {
      const operatorId = this.selectedOperator.id;
      this.newSimService.orderNewSim(operatorId, this.newSim).subscribe((response: string) => {
        console.log('Order success:', response);
      });
    } else {
      console.log('Please select a network operator');
    }
  }
}
