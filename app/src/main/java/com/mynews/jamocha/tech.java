package com.mynews.jamocha;

public class tech {
    private String pname,passcode,rating,canceled,contactnumber,phone,services,instrucions,amount,status,staterted,completed,carnumber,carcompany, description, price, image,model, category, pid, date, time,name,street,city,state,askingrate,phonenumber,type,gpslatitude,gpslongitude;

    public tech() {
    }

    public tech(String pname,String rating,String passcode,String  canceled,String phone,String contactnumber,String instrucions,String services,String amount,String staterted,String completed,String status,String carcompany,String carnumber,String model, String description, String price, String image, String category, String pid, String date, String time,String name,String street,String city,String state,String askingrate,String phonenumber,String type,String gpslatitude,String gpslongitude) {
        this.pname = pname;
        this.status=status;
        this.rating=rating;
        this.canceled=canceled;
        this.staterted=staterted;
        this.completed=completed;
        this.passcode=passcode;
        this.amount=amount;
        this.phone=phone;
        this.contactnumber=contactnumber;
        this.instrucions=instrucions;
        this.services = services;
        this.description = description;
        this.price = price;
        this.image = image;
        this.model=model;
        this.category = category;
        this.pid = pid;
        this.date = date;
        this.time = time;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.askingrate = askingrate;
        this.phonenumber = phonenumber;
        this.type=type;
        this.gpslatitude=gpslatitude;
        this.gpslongitude = gpslongitude;
        this.carcompany=carcompany;
        this.carnumber=carnumber;
    }
    public String getAskingrate() {
        return askingrate;
    }
    public String getPasscode() {
        return passcode;
    }
    public String getRating() {
        return rating;
    }

    public String getCanceled() {
        return canceled;
    }
    public String getphone() {
        return phone;
    }
    public String getContactnumber() {
        return contactnumber;
    }
    public String getInstrucions() {
        return instrucions;
    }
    public String getServices() {
        return services;
    }
    public String getAmount() {
        return amount;
    }
    public String getmodel() {
        return model;
    }
    public String getStaterted() {
        return staterted;
    }
    public String getCompleted() {
        return completed;
    }
    public String getStatus() {
        return status;
    }
    public String getCarnumber() {
        return carnumber;
    }
    public String getCarcompany() {
        return carcompany;
    }
    public String getGpslatitude() {
        return gpslatitude;
    }
    public String getGpslongitude() {
        return gpslongitude;
    }
    public String gettype() {
        return type;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getname() {
        return name;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
