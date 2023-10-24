import { Component, OnInit } from '@angular/core';
import { NewSim } from '../models/NewSim';
import { NetworkOperator } from '../models/NetworkOperator';
import { NewSimService } from '../services/new-sim.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';

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
    existingNumber: '',
    type: ''
  };
  selectedOperatorCircle: string = '';
  selectedOperator: string = '';
  uniqueOperators: string[] = [];
  availableOperatorCircle: string[] = [];
  availableOperators: NetworkOperator[] = [];

  constructor(private newSimService: NewSimService, private dialog: MatDialog) { }

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

  orderNewSim() {
    if (this.selectedOperator) {
      const selectedOperatorObj = this.availableOperators.find(operator => operator.operator === this.selectedOperator);
      if (selectedOperatorObj) {
        const operatorId = selectedOperatorObj.id;
        this.newSimService.orderNewSim(operatorId, this.newSim).subscribe((response: string) => {
          console.log('Order success:', response);
        });
      } else {
        console.log('Selected operator not found in available operators.');
      }
    } else {
      console.log('Please select a network operator');
    }
  }

  openConfirmationDialog(): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '450px',
      data: 'Do you really want to submit?'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.orderNewSim();
      } else {
      }
    });
  }
}
