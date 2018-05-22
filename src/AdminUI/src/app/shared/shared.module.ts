import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";
import {MatTableModule, MatToolbarModule} from '@angular/material';
import {ParkingService} from "./services/parking.service";

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  exports: [
    MatTableModule,
    MatToolbarModule,
  ],
  declarations: [],
  providers: [ParkingService]
})
export class SharedModule {
}
