import {Component, OnInit} from "@angular/core";
import {ParkingService} from "../../shared/services/parking.service";
import {CarPlate} from "../../shared/models/car-plate";
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'ps-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  private carPlates: CarPlate[];
  dataSource: MatTableDataSource<CarPlate>;
  displayedColumns: string[];

  constructor(private parkingService: ParkingService) {
  }

  ngOnInit() {
    this.displayedColumns = ['id', 'localDateTime', 'plate'];
    this.parkingService.getAllData().subscribe(data => {
      this.carPlates = data;
      this.dataSource = new MatTableDataSource(this.carPlates);
    }, () => {
      this.carPlates = [
        {'id': 1, 'localDateTime': '20/05/2018', 'plate': 'WE 23455'},
        {'id': 2, 'localDateTime': '21/05/2018', 'plate': 'KR 23455'},
      ];
      this.dataSource = new MatTableDataSource(this.carPlates);
    });
  }
}
