package eims.service;

import java.util.LinkedHashSet;
import java.util.Set;
import eims.model.security.AuthGender;
import eims.model.security.AuthMenu;
import eims.model.security.AuthRole;
import eims.model.security.AuthUser;
import java.util.SortedSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("procServiceDef")
@Transactional(readOnly = true)
public class ProcServiceDefImpl implements ProcServiceDef {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void fastMenuGen(SortedSet<String> list) {

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            int koto = em.createQuery("DELETE FROM " + AuthMenu.class.getSimpleName()).executeUpdate();
            em.getTransaction().commit();
            System.out.println("macsay: ok db menu drop: " + koto);
        } catch (Exception e) {
            System.out.println("macsay: err db menu drop: " + e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        try {
            em.getTransaction().begin();

            for (String string : list) {
                String dn = null;
                try {
                    dn = string.substring(1, string.lastIndexOf("index") - 1);
                } catch (Exception e) {
                    //System.out.println("hhhhhhhhhhhhhhhh" + e + " bb: " + string);
                }

                if (dn == null || dn.isEmpty()) {
                    continue;
                }

                dn = getShowTitle(dn);
                AuthMenu authMenu = new AuthMenu(dn, string);
                authMenu.setDisplayIconClass("fa fa-list");
                authMenu.setIsActive(Boolean.TRUE);
                em.persist(authMenu);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("macsay: err db menu create: " + e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void dummyUserData() {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();

            AuthRole authRoleAdmin = new AuthRole("ADMIN");
            em.persist(authRoleAdmin);
            AuthRole authRoleUser = new AuthRole("USER");
            em.persist(authRoleUser);

            Set<AuthRole> authRoles = new LinkedHashSet<>();
            authRoles.add(authRoleAdmin);
            authRoles.add(authRoleUser);

            AuthUser authUser1 = new AuthUser();
            authUser1.setPassword("123");
            authUser1.setUsername("mac");
            authUser1.setPicFile("aaaa.jpg");
            authUser1.setEnabled(true);
            authUser1.setAccountNonExpired(false);
            authUser1.setAccountNonLocked(false);
            authUser1.setCredentialsNonExpired(false);
            authUser1.setAuthRoles(authRoles);
            authUser1.setDisplayName("Manik");
            authUser1.setGender(AuthGender.MALE);
            authUser1.setFullName("Mohammad Badiuzzaman");
            authUser1.setEmail("manikmonir@gmail.com");
            em.persist(authUser1);

            authRoles = new LinkedHashSet<>();
            authRoles.add(authRoleUser);

            AuthUser authUser2 = new AuthUser();
            authUser2.setPassword("123");
            authUser2.setUsername("saif_hmk");
            authUser2.setPicFile("bbbb.jpg");
            authUser2.setEnabled(true);
            authUser2.setAccountNonExpired(false);
            authUser2.setAccountNonLocked(false);
            authUser2.setCredentialsNonExpired(false);
            authUser2.setAuthRoles(authRoles);
            authUser2.setDisplayName("Saif");
            authUser2.setGender(AuthGender.MALE);
            authUser2.setFullName("MD. HOSHEN MAHMUD KHAN");
            authUser2.setEmail("saif_hmk@live.com");
            em.persist(authUser2);

            AuthUser authUser3 = new AuthUser();
            authUser3.setPassword("123");
            authUser3.setUsername("anis");
            authUser3.setPicFile("cccc.jpg");
            authUser3.setEnabled(true);
            authUser3.setAccountNonExpired(false);
            authUser3.setAccountNonLocked(false);
            authUser3.setCredentialsNonExpired(false);
            authUser3.setAuthRoles(authRoles);
            authUser3.setDisplayName("Anis");
            authUser3.setGender(AuthGender.MALE);
            authUser3.setFullName("Mohammad Anisur Rahman Khan");
            authUser3.setEmail("anis.object@gmail.com");
            em.persist(authUser3);

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("macsay: err db users create: " + e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    private static String getShowTitle(String fdf) {

        if (fdf == null) {
            return "";
        }

        String uuu = "";
        for (int i = 0; i < fdf.length(); i++) {
            char hhh = fdf.charAt(i);

            if (i == 0) {
                uuu += ("" + hhh).toUpperCase();
                continue;
            }

            if (hhh >= 65 && hhh <= (65 + 26)) {
                uuu += " " + hhh;
            } else {
                uuu += "" + hhh;
            }
        }

        return uuu;
    }
}
