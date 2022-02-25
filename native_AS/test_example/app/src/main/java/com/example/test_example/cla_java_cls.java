package com.example.test_example;

public class cla_java_cls {
    double calcInvest(double a, double r, double n){
        return a * Math.pow(1+r, n) ;
    }

    double calcLoan(double a, double r, double n){
        return a * (Math.pow((1 + r / 12), (n * 12)) * (r / 12)) / (Math.pow((1 + r / 12), (n * 12) ) -1 );
    }
}
