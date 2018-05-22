import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.prod";
import {CarPlate} from "../models/car-plate";
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ParkingService {
  private baseUrl: string;

  constructor(private httpClient: HttpClient) {
    this.baseUrl = environment.parkingServiceUrl;
  }

  public getAllData(): Observable<CarPlate[]> {
    return this.httpClient.get<CarPlate[]>(this.baseUrl + environment.imagesPath);
  }
}
