import { Component, OnInit } from '@angular/core';
import { NewSim } from '../models/NewSim';
import { NetworkOperator } from '../models/NetworkOperator';
import { NewSimService } from '../services/new-sim.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { Router } from '@angular/router';

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
    type: '',
    orderID: ''
  };
  selectedOperatorCircle: string = '';
  selectedOperator: string = '';
  uniqueOperators: string[] = [];
  availableOperatorCircle: string[] = [];
  availableOperators: NetworkOperator[] = [];
  cachedorderID: string = '';
  response?: string;
  constructor(private newSimService: NewSimService, private dialog: MatDialog, private router:Router) { }

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
        this.newSim.orderID = this.cachedorderID;
        this.newSimService.setOrderId(this.cachedorderID);
        this.newSimService.orderNewSim(operatorId, this.newSim).subscribe(()=>{
          this.router.navigate(['/order-status']);
        });
      }
    }
  }

  openConfirmationDialog(): void {
    this.newSimService.getOrderID().subscribe(id => { this.cachedorderID = id.orderID });
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '450px',
      data: 'Do you want to continue?'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.orderNewSim();
      } else {
      }
    });
  }
}
