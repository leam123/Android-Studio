package com.example.registration;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

//import java.util.ArrayList;

public class Account implements Parcelable {
        private String name;
        private String age;
        private String birthDate;
        private String gender;
        private String nationality;
        private String elem;
        private String year1;
        private String junior;
        private String year2;
        private String senior;
        private String year3;
        private String college;
        private String year4;

        /*String[] skills = new String[20];
        private int[] pics = new int[20];
        private int sCount = 0;
        private int pCount = 0;

        public String[] getSkills(){
            return this.skills;
        }
        public int[] getPics(){
            return this.pics;
        }*/

        @Override
        public int describeContents(){
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags){
            dest.writeString(name);
            dest.writeString(age);
            dest.writeString(birthDate);
            dest.writeString(gender);
            dest.writeString(nationality);
            dest.writeString(elem);
            dest.writeString(year1);
            dest.writeString(junior);
            dest.writeString(year2);
            dest.writeString(senior);
            dest.writeString(year3);
            dest.writeString(college);
            dest.writeString(year4);
          //  dest.writeStringArray(skills);
           // dest.writeIntArray(pics);
        }

        //default constructor
        public Account(){}


       /*public boolean addSkill(String str){
            boolean ok = false;
            if(!contain(str)){
                skills[sCount] = str;
                sCount++;
            }
            return ok;
        }

        public boolean addImage(int i){
            boolean ok = false;
            if(!contains(i)){
                pics[pCount] = i;
                pCount++;
            }
            return ok;
        }

        public boolean contain(String str){
            boolean ok = false;
            for(String s: skills){
                if(str.equals(s)){
                    ok = true;
                    break;
                }
            }

            return ok;
        }

        public boolean contains(int i){
            boolean ok = false;
            for(int x: pics){
                if(x == i){
                    ok = true;
                    break;
                }
            }

            return ok;
        }*/
        //new constructor
        /*public Account(String name, String age, String birthDate, String gender, String nationality,
                       String elem, String year1, String junior, String year2, String senior, String year3,
                       String college, String year4) {
            this.name = name;
            this.age = age;
            this.birthDate = birthDate;
            this.gender = gender;
            this.nationality = nationality;
            this.elem = elem;
            this.year1 = year1;
            this.junior = junior;
            this.year2 = year2;
            this.senior = senior;
            this.year3 = year3;
            this.college = college;
            this.year4 = year4;
        }*/

        //getters
        public String getName() { return name; }
        public String getAge() { return age; }
        public String getBirthDate() { return birthDate; }
        public String getGender() { return gender; }
        public String getNationality() { return nationality; }
        public String getElem() { return elem; }
        public String getYear1() { return year1; }
        public String getJunior() { return junior; }
        public String getYear2() { return year2; }
        public String getSenior() { return senior; }
        public String getYear3() { return year3; }
        public String getCollege() { return college; }
        public String getYear4() { return year4; }
        //public String[] getSkills() { return skills; }
        //public int[] getPics() { return pics; }

        //setters
        public void setName(String name) { this.name = name; }
        public void setAge(String age) { this.age = age; }
        public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
        public void setGender(String gender) { this.gender = gender; }
        public void setNationality(String nationality) { this.nationality = nationality; }
        public void setElem(String elem) { this.elem = elem; }
        public void setYear1(String year1) { this.year1 = year1; }
        public void setJunior(String junior) { this.junior = junior; }
        public void setYear2(String year2) { this.year2 = year2; }
        public void setSenior(String senior) { this.senior = senior; }
        public void setYear3(String year3) { this.year3 = year3; }
        public void setCollege(String college) { this.college = college; }
        public void setYear4(String year4) { this.year4 = year4; }
        //public void setSkills(String[] skills) { this.skills = skills; }
        //public void setPics(int[] pics) { this.pics = pics; }


        /*public boolean contain(String arr){
            boolean ok = false;
            for(String str: skills){
                if(arr.equals(str)){
                    ok = true;
                }
            }
            return ok;
        }

        public boolean add(String arr){
            boolean ok = false;
            for(!contain(arr)){
                skills[sCount] = arr;
                sCount++;
            }
            return ok;
        }

        public boolean addPic(int index){
            boolean ok = false;
            for(!containPic(index)){
                pics[pCount] = index;
                pCount++;
            }
            return ok;
        }

        public boolean containPic(int index){
            boolean ok = false;
            for(int x: pics){
                if(index == x){
                    ok = true;
                }
            }
            return ok;
        }*/

        //Parcelling part
        public Account(Parcel in){
            this.name = in.readString();
            this.age = in.readString();
            this.birthDate = in.readString();
            this.gender = in.readString();
            this.nationality = in.readString();
            this.elem = in.readString();
            this.year1 = in.readString();
            this.junior = in.readString();
            this.year2 = in.readString();
            this.senior = in.readString();
            this.year3 = in.readString();
            this.college = in.readString();
            this.year4 = in.readString();;
            //this.skills = in.createStringArray();
            //this.pics = in.createIntArray();
        }

        public static final Parcelable.Creator<Account> CREATOR =  new Parcelable.Creator<Account>(){
            @Override
            public Account createFromParcel(Parcel parcel){
                return new Account(parcel);
            }
            //implement this method
            @Override
            public Account[] newArray(int size){
                return new Account[size];
            }
        };
}
