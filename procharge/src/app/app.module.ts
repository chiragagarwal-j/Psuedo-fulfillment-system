import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatStepperModule } from '@angular/material/stepper';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';


import { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';

import { NavbarComponent } from './navbar/navbar.component';
import { GetNewSimComponent } from './get-new-sim/get-new-sim.component';
import { RechargeComponent } from './recharge/recharge.component';
import { HomeComponent } from './home/home.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { TrackOrderComponent } from './track-order/track-order.component';
import { RechargePlansComponent } from './recharge-plans/recharge-plans.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    GetNewSimComponent,
    RechargeComponent,
    HomeComponent,
    ConfirmationDialogComponent,
    TrackOrderComponent,
    RechargePlansComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSelectModule,
    MatInputModule,
    MatRadioModule,
    MatStepperModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSnackBarModule,
    MatGridListModule,
    MatCardModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
