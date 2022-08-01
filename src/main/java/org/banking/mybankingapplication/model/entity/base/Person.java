package org.banking.mybankingapplication.model.entity.base;


import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {
}
