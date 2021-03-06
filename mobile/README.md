# SmartOutlet-IOT

Mobile (Android)  
-----

Mobile application that was implemented for the smart outlet devices.    

Application screens  
-----

<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/android%20app%20screenshots/hm_sc_1.jpg" width="24%"></img>
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/android%20app%20screenshots/hm_sc_2.jpg" width="24%"></img>
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/android%20app%20screenshots/hm_sc_4.jpg" width="24%"></img>
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/android%20app%20screenshots/hm_sc_3.jpg" width="24%"></img>
    
Architecture
-----
For this project I've used MVVM with clean architecture approach

<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/clean_mvvm.png" width="85%"></img>

Project Structure
-----

```bash
├── src 
    ├── common
    ├── data
    │   ├── mapper
    │   ├── model
    │   ├── repository
    │   └── service
    │
    ├── di 
    ├── domain 
    │   ├── model
    │   ├── repository
    │   └── usecase
    │
    ├── presentation
    │   ├── adddevice
    │   ├── gateway
    │   ├── navigation
    │   └── overview
    ├── utils
    └── Application
```


Resources
-----

Articles:     
[Android Clean MVVM](https://josipsalkovic.com/2019/12/15/android-clean-mvvm-architecture/)       
[HTTP erros handling with Kotlin, Retrofit and RX](https://github.com/janczar/kotlinrx)      
[android-clean-mvvm-architecture](https://josipsalkovic.com/2019/12/15/android-clean-mvvm-architecture/)  

Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>     
Icons made by <a href="https://www.flaticon.com/authors/srip" title="srip">srip</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
