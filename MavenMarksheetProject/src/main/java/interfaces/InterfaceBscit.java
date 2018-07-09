/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import models.Bscit;
import java.util.List;

/**
 *
 * @author zhee
 */
public interface InterfaceBscit {
    List<Bscit> select();
    List<Bscit> select(String sem);
    void saveOrUpdate(Bscit b);
    void delete(Bscit b);
}
