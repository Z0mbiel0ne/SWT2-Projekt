/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.view.UpdateCredit;
import javax.swing.JTable;

/**
 * UpdateCreditFacory
 * @author Marcel
 */
public class UpdateCreditFactory 
{
  UpdateCredit updateCredit;
  
  /**
   * Creates a new Instance of UpdateCredit
   * @param value UserID
   * @param jtable1 Table of Kunden
   * @return UpdateCreditView
   */
  public  UpdateCredit newInsatnce(int value, JTable jtable1)
  {
      updateCredit = new UpdateCredit(value, jtable1);
      return updateCredit;
  }
}
