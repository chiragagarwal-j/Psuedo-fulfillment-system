import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetNewSimComponent } from './get-new-sim/get-new-sim.component';
import { RechargeComponent } from './recharge/recharge.component';
import { HomeComponent } from './home/home.component';
import { TrackOrderComponent } from './track-order/track-order.component';
import { RechargePlansComponent } from './recharge-plans/recharge-plans.component';
import { OrderStatusComponent } from './order-status/order-status.component';
import { PaymentPageComponent } from './payment-page/payment-page.component';
import { RechargeOrderStatusComponent } from './recharge-order-status/recharge-order-status.component';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "home", component: HomeComponent },
  { path: "getnewsim", component: GetNewSimComponent },
  { path: "recharge", component: RechargeComponent },
  { path: "trackOrder", component: TrackOrderComponent },
  { path: 'recharge-plans/:operator', component: RechargePlansComponent },
  { path: "order-status", component: OrderStatusComponent },
  { path: "payment-page", component: PaymentPageComponent },
  { path: "recharge-order-status", component: RechargeOrderStatusComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
