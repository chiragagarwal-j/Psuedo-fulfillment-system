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
  selectedBrand: string | null = null;
  selectedOperator: NetworkOperator | null = null;
  uniqueBrands: string[] = [];
  availableOperators: NetworkOperator[] = [];

  constructor(private newSimService: NewSimService,private dialog:MatDialog) {}

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

  openConfirmationDialog(): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '450px', // Set the desired width
      data: 'Do you really want to submit?' // Pass data to the dialog
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Handle the submission logic here
        // This block will be executed if the user confirms the action
      } else {
        // Handle the case when the user cancels the action
      }
    });
  }

}