/*
 * The MIT License
 *
 * Copyright 2014 umireon.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package info.umireon.sw_library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


/**
 *
 * @author umireon
 */
public class BoundaryLend {
    private final ControlMaterial ctrlMaterial;
    private final ControlUser ctrlUser;
    private final InputStream stdin;
    private final PrintStream stdout;

    public BoundaryLend(final ControlMaterial ctrlMaterial,
            final ControlUser ctrlUser,
            final InputStream stdin, final PrintStream stdout) {
        this.ctrlMaterial = ctrlMaterial;
        this.ctrlUser = ctrlUser;
        this.stdin = stdin;
        this.stdout = stdout;
    }

    public void listen() {
        stdout.println("資料貸出システム (v0.0.1)");
        InputStreamReader isr = new InputStreamReader(stdin);
        BufferedReader br = new BufferedReader(isr);
        
        indexMaterials();
        indexUsers();

        try {
            stdout.print("貸出者氏名> ");
            String borrowerName = br.readLine();
            if (borrowerName == null) System.exit(0);
            EntityUser borrower = ctrlUser.getUser(borrowerName);
            if (borrower == null) {
                ctrlUser.addUser(new EntityUser(borrowerName));
                borrower = ctrlUser.getUser(borrowerName);
                noticeUserRegistration(borrower);
            }

            stdout.print("貸出資料名> ");
            String materialName = br.readLine();
            if (materialName == null) return;
            ctrlMaterial.lendMaterial(materialName, borrower);
            indexMaterials();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnknownMaterialException e) {
            stdout.println("指定された資料は存在しません");
        } catch (UnavailableMaterialException e) {
            stdout.println("指定された資料は貸出できません");
        } catch (ReservedMaterialException e) {
            stdout.println("指定された資料は予約されています");
        }
    }
    
    private void indexMaterials() {
        stdout.println("資料一覧:");
        for (EntityMaterial material : ctrlMaterial.getMaterials()) {
            StringBuilder str = new StringBuilder();
            str.append("  - ");
            str.append(material.getName());
            if (material.getStatus() != null) {
                str.append(" ");
                str.append(material.getStatus().toString());
            }
            if (material.isReserved()) {
                str.append(" 予約(");
                str.append(material.peekReservation().getReserver().getName());
                str.append(")");
            }
            stdout.println(str.toString());
        }
    }
    
    private void indexUsers() {
        stdout.println("利用者一覧:");
        for (EntityUser user : ctrlUser.getUsers()) {
            StringBuilder str = new StringBuilder();
            str.append("  - ");
            str.append(user.getName());
            stdout.println(str.toString());
        }
    }
    
    private void noticeUserRegistration(EntityUser registered) {
        stdout.println("次のユーザが登録されました: " + registered.getName());
    }
}
