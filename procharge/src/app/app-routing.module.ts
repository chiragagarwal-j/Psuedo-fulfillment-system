import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetNewSimComponent } from './get-new-sim/get-new-sim.component';
import { RechargeComponent } from './recharge/recharge.component';
import { HomeComponent } from './home/home.component';
import { TrackOrderComponent } from './track-order/track-order.component';
import { RechargePlansComponent } from './recharge-plans/recharge-plans.component';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "home", component: HomeComponent },
  { path: "getnewsim", component: GetNewSimComponent },
  { path: "recharge", component: RechargeComponent },
  { path: "trackOrder", component: TrackOrderComponent },
  { path: 'recharge-plans/:operator', component: RechargePlansComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
