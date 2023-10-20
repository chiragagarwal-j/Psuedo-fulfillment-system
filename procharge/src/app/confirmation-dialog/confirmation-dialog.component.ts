import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { GetNewSimComponent } from '../get-new-sim/get-new-sim.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent {
  durationInSeconds = 5;

  constructor(private snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<GetNewSimComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string
  ) { }

  openSnackBar() {
    this.snackBar.open('Order Received!', 'Close', {
      duration: 3000,
    });
    this.dialogRef.close(true);
  }
}