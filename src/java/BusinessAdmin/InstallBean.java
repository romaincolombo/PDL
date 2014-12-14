/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessAdmin;

import Entity.Book;
import Entity.Category;
import Entity.Customer;
import Entity.CustomerOrder;
import Entity.OrderLine;
import Entity.OrderLinePK;
import Facade.CategoryFacade;
import Utils.PasswordHash;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author colombor
 */
@ManagedBean
@RequestScoped
public class InstallBean {
    @EJB
    private CategoryFacade categoryFacade;
    @PersistenceContext(unitName = "PDL_PU")
    private EntityManager em;
    
    @Resource
    UserTransaction ut;
    
    @Resource
    private javax.transaction.UserTransaction utx;

    
    /**
     * Creates a new instance of InstallBean
     */
    public InstallBean() {
    }
    
    public String getInstallDB() {
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            ut.begin();
            
            
            Category cat_test1 = new Category();
            cat_test1.setName("Littérature");
            cat_test1.setDescription("Des sentiments, des histoires et des vies.");
            em.persist(cat_test1);
            
            Category cat_test2 = new Category();
            cat_test2.setName("Romans historiques");
            cat_test2.setDescription("Redécouvrez une partie de l'histoire française à travers cette sélection.");
            em.persist(cat_test2);
            
            Category cat_test3 = new Category();
            cat_test3.setName("Bandes dessinées");
            cat_test3.setDescription("Des scénarios captivants et des dessins chaleureux.");
            em.persist(cat_test3);
            
            Category cat_test4 = new Category();
            cat_test4.setName("Policier et Suspense");
            cat_test4.setDescription("Des livres qui tiennent en haleine du début à la fin.");
            em.persist(cat_test4);
            
            Category cat_test5 = new Category();
            cat_test5.setName("Livres pour enfants");
            cat_test5.setDescription("Pour éveiller et émerveiller les plus jeunes de votre famille.");
            em.persist(cat_test5);
            
            Category cat_test6 = new Category();
            cat_test6.setName("Romans pour adolescents");
            cat_test6.setDescription("L'apprentissage de la vie et de l'amour à travers de belles histoires.");
            em.persist(cat_test6);
            
            em.flush();
            
            Book book_test1 = new Book();
            book_test1.setCategory(cat_test2);
            book_test1.setTitle("Le siècle, Tome 3 : Aux portes de l'éternité");
            book_test1.setDescription("De l'édification du mur de Berlin à l'effondrement de l'Union soviétique, la grande aventure du XXe siècle telle que personne ne l'a jamais racontée. 1961. Les Allemands de l'Est ferment l'accès à Berlin-Ouest. La tension entre les États-Unis et l'Union soviétique s'exacerbe pour atteindre un point culminant l'année suivante avec la crise des missiles de Cuba. Le monde scindé en deux blocs se livre une guerre froide qui risque de devenir une guerre nucléaire.");
            book_test1.setAuthor("Ken Follett");
            book_test1.setPublisher("Robert Laffont");
            book_test1.setDateBook(textFormat.parse("2014-09-25"));
            book_test1.setPrice(BigDecimal.valueOf(24.9));
            book_test1.setRating(10);
            book_test1.setLanguageBook("Français");
            book_test1.setStock(10);
            book_test1.setIsbn("978-2221110843");
            book_test1.setHardcover(1213);
            book_test1.setNewSelection(true);
            book_test1.setImage("cover-portes.jpg");
            em.persist(book_test1);
            
            Book book_test2 = new Book();
            book_test2.setCategory(cat_test1);
            book_test2.setTitle("Demain est un autre jour");
            book_test2.setDescription("À la mort de sa mère, Brett Bohlinger pense qu’elle va hériter de l’empire de cosmétique familial. Mais, à sa grande surprise, elle ne reçoit qu’un vieux papier jauni et chiffonné : la liste des choses qu’elle voulait vivre, rédigée lorsqu’elle avait 14 ans. Pour toucher sa part d’héritage, elle aura un an pour réaliser tous les objectifs de cette life list... Mais la Brett d’aujourd’hui n’a plus rien à voir avec la jeune fille de l’époque.");
            book_test2.setAuthor("Lori Nelson");
            book_test2.setPublisher("Pocket");
            book_test2.setDateBook(textFormat.parse("2014-04-03"));
            book_test2.setPrice(BigDecimal.valueOf(7.90));
            book_test2.setRating(10);
            book_test2.setLanguageBook("Français");
            book_test2.setStock(125);
            book_test2.setIsbn("978-2266236799");
            book_test2.setHardcover(445);
            book_test2.setNewSelection(true);
            book_test2.setImage("cover-demain-autre-jour.jpg");
            em.persist(book_test2);
            
            Book book_test3 = new Book();
            book_test3.setCategory(cat_test3);
            book_test3.setTitle("Largo Winch - tome 19 - Chassé-Croisé");
            book_test3.setDescription("Accompagné de Dwight Cochrane, de Miss Pennywinkle et de Silky, Largo Winch se rend à Londres pour présider le Big Board du groupe W, à l'occasion duquel il espère bien convaincre Laurent Draillac, avionneur français, de conclure un joint venture avec la branche aéronautique de son groupe. Au programme : réunions de travail et détente. Mais rapidement, la situation se corse.");
            book_test3.setAuthor("Jean Van Hamme (Auteur), Philippe Francq (Illustrations)");
            book_test3.setPublisher("Dupuis");
            book_test3.setDateBook(textFormat.parse("2014-11-14"));
            book_test3.setPrice(BigDecimal.valueOf(13.95));
            book_test3.setRating(8);
            book_test3.setLanguageBook("Français");
            book_test3.setStock(1);
            book_test3.setIsbn("978-2800159317");
            book_test3.setHardcover(48);
            book_test3.setNewSelection(true);
            book_test3.setImage("cover-largo.jpg");
            em.persist(book_test3);
            
            Book book_test4 = new Book();
            book_test4.setCategory(cat_test4);
            book_test4.setTitle("Angor");
            book_test4.setDescription("Camille Thibault est gendarme dans le nord de la France. Depuis sa greffe du cœur, ses collègues s'inquiètent pour elle. Chaque nuit, elle fait des cauchemars où une femme séquestrée l'appelle au secours. Un rêve tellement vrai, comme un souvenir... celui de son donneur ? Camille n'a plus qu'une obsession : retrouver son identité et découvrir quel drame il a vécu... Au même moment, à une centaine de kilomètres de là, deux employés de l'Office National des Forêts constatent les dégâts des orages violents survenus en ce mois d'août.");
            book_test4.setAuthor("Franck Thilliez");
            book_test4.setPublisher("Fleuve Editions");
            book_test4.setDateBook(textFormat.parse("2014-10-09"));
            book_test4.setPrice(BigDecimal.valueOf(21.90));
            book_test4.setRating(10);
            book_test4.setLanguageBook("Français");
            book_test4.setStock(55);
            book_test4.setIsbn("978-2265098695");
            book_test4.setHardcover(624);
            book_test4.setNewSelection(true);
            book_test4.setImage("cover-angor.jpg");
            em.persist(book_test4);
            
            Book book_test5 = new Book();
            book_test5.setCategory(cat_test5);
            book_test5.setTitle("Saccage ce carnet");
            book_test5.setDescription("Saccage ce carnet est un livre illustré qui s'adresse a tous ceux qui ont eu un jour secrètement l'envie de commencer, poursuivre et achever un journal ou un carnet de croquis. Le fil des propositions amène le lecteur à mobiliser toutes ses forces et à puiser dans sa créativite pour corriger ses erreurs, progresser dans le remplissage des pages (et leur destruction). ");
            book_test5.setAuthor("Keri Smith");
            book_test5.setPublisher("Perigee Trade");
            book_test5.setDateBook(textFormat.parse("2012-12-04"));
            book_test5.setPrice(BigDecimal.valueOf(11.55));
            book_test5.setRating(10);
            book_test5.setLanguageBook("Français");
            book_test5.setStock(4);
            book_test5.setIsbn("978-0399162862");
            book_test5.setHardcover(224);
            book_test5.setNewSelection(false);
            book_test5.setImage("cover-saccage.jpg");
            em.persist(book_test5);
            
            Book book_test6 = new Book();
            book_test6.setCategory(cat_test6);
            book_test6.setTitle("L'épreuve : Le labyrinthe");
            book_test6.setDescription("Thomas, dont la mémoire a été effacée, se réveille un jour dans un nouveau monde où vivent une cinquantaine d'enfants. Il s'agit d'une ferme située au centre d'un labyrinthe peuplé de monstres d'acier terrifiants. Les ados n'ont aucun souvenir de leur vie passée et ne comprennent pas ce qu'ils font là. Ils n'ont qu'un seul désir, trouver la sortie.");
            book_test6.setAuthor("James Dashner");
            book_test6.setPublisher("Pocket Jeunesse");
            book_test6.setDateBook(textFormat.parse("2012-10-04"));
            book_test6.setPrice(BigDecimal.valueOf(18.50));
            book_test6.setRating(10);
            book_test6.setLanguageBook("Français");
            book_test6.setStock(15);
            book_test6.setIsbn("978-2266200127");
            book_test6.setHardcover(404);
            book_test6.setNewSelection(false);
            book_test6.setImage("cover-laby.jpg");
            em.persist(book_test6);
            
            Book book_test7 = new Book();
            book_test7.setCategory(cat_test6);
            book_test7.setTitle("Nos étoiles contraires");
            book_test7.setDescription("Hazel, 16 ans, est atteinte d'un cancer. Son dernier traitement semble avoir arrêté l'évolution de la maladie, mais elle se sait condamnée. Bien qu'elle s'y ennuie passablement, elle intègre un groupe de soutien, fréquenté par d'autres jeunes malades. C'est là qu'elle rencontre Augustus, un garçon en rémission, qui partage son humour et son goût de la littérature. Entre les deux adolescents, l'attirance est immédiate.");
            book_test7.setAuthor("John Green (Auteur), Catherine Gibert (Traduction)");
            book_test7.setPublisher("Nathan");
            book_test7.setDateBook(textFormat.parse("2013-02-21"));
            book_test7.setPrice(BigDecimal.valueOf(16.9));
            book_test7.setRating(10);
            book_test7.setLanguageBook("Français");
            book_test7.setStock(1);
            book_test7.setIsbn("978-2092543030");
            book_test7.setHardcover(330);
            book_test7.setNewSelection(false);
            book_test7.setImage("cover-etoiles.jpg");
            em.persist(book_test7);
            
            Book book_test8 = new Book();
            book_test8.setCategory(cat_test1);
            book_test8.setTitle("Central Park");
            book_test8.setDescription("Ce livre de test est une mise en abime pour pouvoir tester notre propre application.");
            book_test8.setAuthor("Guillaume Musso");
            book_test8.setPublisher("XO");
            book_test8.setDateBook(textFormat.parse("2014-03-28"));
            book_test8.setPrice(BigDecimal.valueOf(21.9));
            book_test8.setRating(8);
            book_test8.setLanguageBook("Français");
            book_test8.setStock(125);
            book_test8.setIsbn("978-2845636767");
            book_test8.setHardcover(400);
            book_test8.setNewSelection(true);
            book_test8.setImage("cover-central.jpg");
            em.persist(book_test8);
            
            Book book_test9 = new Book();
            book_test9.setCategory(cat_test3);
            book_test9.setTitle("Blake & Mortimer - tome 23 - Le Bâton de Plutarque");
            book_test9.setDescription("1944 : le capitaine Blake réussit, aux commandes d'un prototype, à déjouer une attaque suicide contre le Parlement anglais. Un exploit qui lui vaut d'être recruté par le MI 6. Sa mission ? Faire gagner aux Alliés la Deuxième Guerre mondiale et les préparer pour... la troisième ! Arrivé dans l'une des bases secrètes du MI 6, Blake découvre que son partenaire n'est autre que Mortimer, son ami d'enfance. ");
            book_test9.setAuthor("Yves Sente (Auteur), André Juillard (Illustrations)");
            book_test9.setPublisher("Blake et Mortimer");
            book_test9.setDateBook(textFormat.parse("2014-12-05"));
            book_test9.setPrice(BigDecimal.valueOf(15.95));
            book_test9.setRating(8);
            book_test9.setLanguageBook("Français");
            book_test9.setStock(10);
            book_test9.setIsbn("978-2870971932");
            book_test9.setHardcover(64);
            book_test9.setNewSelection(true);
            book_test9.setImage("cover-blake.jpg");
            em.persist(book_test9);
            
            Book book_test10 = new Book();
            book_test10.setCategory(cat_test4);
            book_test10.setTitle("Nymphéas noirs");
            book_test10.setDescription("Le jour paraît sur Giverny. Du haut de son moulin, une vieille dame veille, surveille. Le quotidien du village, les cars de touristes... Des silhouettes et des vies. Deux femmes, en particulier, se détachent : l'une, les yeux couleur nymphéas, rêve d'amour et d'évasion ; l'autre, onze ans, ne vit déjà que pour la peinture. Deux femmes qui vont se trouver au cœur d'un tourbillon orageux. Car dans le village de Monet, où chacun est une énigme, où chaque âme a son secret, des drames vont venir diluer les illusions et raviver les blessures du passé...");
            book_test10.setAuthor("Michel Bussi");
            book_test10.setPublisher("Pocket");
            book_test10.setDateBook(textFormat.parse("2013-09-05"));
            book_test10.setPrice(BigDecimal.valueOf(7.7));
            book_test10.setRating(8);
            book_test10.setLanguageBook("Français");
            book_test10.setStock(10);
            book_test10.setIsbn("978-2266222372");
            book_test10.setHardcover(493);
            book_test10.setNewSelection(false);
            book_test10.setImage("cover-nympheas.jpg");
            em.persist(book_test10);
            
            Book book_test11 = new Book();
            book_test11.setCategory(cat_test1);
            book_test11.setTitle("Le jour où j'ai appris à vivre");
            book_test11.setDescription("Imaginez : vous vous baladez sur les quais de San Francisco un dimanche, quand soudain une bohémienne vous saisit la main pour y lire votre avenir. Amusé, vous vous laissez faire, mais dans l’instant son regard se fige, elle devient livide.");
            book_test11.setAuthor("Laurent Gounelle");
            book_test11.setPublisher("Kero");
            book_test11.setDateBook(textFormat.parse("2014-10-02"));
            book_test11.setPrice(BigDecimal.valueOf(9.90));
            book_test11.setRating(8);
            book_test11.setLanguageBook("Français");
            book_test11.setStock(40);
            book_test11.setIsbn("978-2366580983");
            book_test11.setHardcover(288);
            book_test11.setNewSelection(true);
            book_test11.setImage("cover-jour.jpg");
            em.persist(book_test11);
            
            Book book_test12 = new Book();
            book_test12.setCategory(cat_test3);
            book_test12.setTitle("XIII (13), tome 23 : Le message du martyr");
            book_test12.setDescription("Le duo Sente et Jigounov en fait voir de toutes les couleurs à XIII dans\n" +
"ce tome 23 ! Betty à l'hôpital, le mafieux Little Joe à ses trousses, le FBI l'accusant toujours d'actes terroristes : il est temps pour lui de quitter les États-Unis. Paris-Bruxelles-Amsterdam-Leyde. Le résultat des recherches de son ami d'enfance Jim Drake et et Annika, la jeune dealeuse aux cheveux roses, entraînent XIII sur les traces de son parrain.");
            book_test12.setAuthor("Yves Sente (Auteur), Iouri Jigounov (Illustrations)");
            book_test12.setPublisher("Dargaud");
            book_test12.setDateBook(textFormat.parse("2014-11-28"));
            book_test12.setPrice(BigDecimal.valueOf(11.99));
            book_test12.setRating(6);
            book_test12.setLanguageBook("Français");
            book_test12.setStock(190);
            book_test12.setIsbn("978-2505060031");
            book_test12.setHardcover(48);
            book_test12.setNewSelection(true);
            book_test12.setImage("cover-xiii.jpg");
            em.persist(book_test12);
            
            Book book_test13 = new Book();
            book_test13.setCategory(cat_test4);
            book_test13.setTitle("L'Enfant aux cailloux");
            book_test13.setDescription("Elsa Préau est une retraitée bien ordinaire. De ces vieilles dames trop seules et qui s'ennuient tellement - surtout le dimanche - qu'elles finissent par observer ce qui se passe chez leurs voisins. Elsa, justement, connaît tout des habitudes de la famille qui vient de s'installer à côté de chez elle. Et très vite, elle est persuadée que quelque chose ne va pas. Les deux enfants ont beau être en parfaite santé, un autre petit garçon apparaît de temps en temps - triste, maigre, visiblement maltraité.");
            book_test13.setAuthor("Sophie Loubière");
            book_test13.setPublisher("Pocket");
            book_test13.setDateBook(textFormat.parse("2014-03-13"));
            book_test13.setPrice(BigDecimal.valueOf(6.8));
            book_test13.setRating(10);
            book_test13.setLanguageBook("Français");
            book_test13.setStock(10);
            book_test13.setIsbn("978-2266246309");
            book_test13.setHardcover(349);
            book_test13.setNewSelection(true);
            book_test13.setImage("cover-enfant.jpg");
            em.persist(book_test13);
            
            Book book_test14 = new Book();
            book_test14.setCategory(cat_test5);
            book_test14.setTitle("Finish This Book");
            book_test14.setDescription("Your mission is to become the new author of this work. You will continue the research and provide the content. In order to complete the task, you will have to undergo some secret intelligence training, which I have included in this volume. Since no one knows what lies ahead, please proceed with caution, but know...");
            book_test14.setAuthor("Keri Smith");
            book_test14.setPublisher("Particular Books");
            book_test14.setDateBook(textFormat.parse("2011-09-06"));
            book_test14.setPrice(BigDecimal.valueOf(11.11));
            book_test14.setRating(8);
            book_test14.setLanguageBook("Anglais");
            book_test14.setStock(60);
            book_test14.setIsbn("978-1846145209");
            book_test14.setHardcover(208);
            book_test14.setNewSelection(false);
            book_test14.setImage("cover-finish.jpg");
            em.persist(book_test14);
            
            Book book_test15 = new Book();
            book_test15.setCategory(cat_test6);
            book_test15.setTitle("L'épreuve : La Terre brûlée");
            book_test15.setDescription("ET SI LA VIE ÉTAIT PIRE HORS DU LABYRINTHE ? Thomas en était sûr, la sortie du labyrinthe marquerait la fin de l'Épreuve. Mais à l'extérieur il découvre un monde ravagé. La terre est dépeuplée, brûlée par un climat ardent. Plus de gouvernement, plus d'ordre... et des hordes de gens infectés, en proie à une folie meurtrière, errent dans les villes en ruines. Au lieu de la liberté espérée, Thomas se trouve confronté à un nouveau défi démoniaque. Au coeur de cette terre brûlée, parviendra-t-il à trouver la paix... et un peu d'amour ?");
            book_test15.setAuthor("James Dashner");
            book_test15.setPublisher("Pocket Jeunesse");
            book_test15.setDateBook(textFormat.parse("2013-08-14"));
            book_test15.setPrice(BigDecimal.valueOf(18.5));
            book_test15.setRating(8);
            book_test15.setLanguageBook("Français");
            book_test15.setStock(35);
            book_test15.setIsbn("978-2266200134");
            book_test15.setHardcover(414);
            book_test15.setNewSelection(false);
            book_test15.setImage("cover-terre.jpg");
            em.persist(book_test15);
            
            Book book_test16 = new Book();
            book_test16.setCategory(cat_test6);
            book_test16.setTitle("Qui es-tu, Alaska ?");
            book_test16.setDescription("Miles Halter a seize ans mais n'a pas l'impression d'avoir vécu. Assoiffé d'expériences, il quitte le cocon familial pour le campus universitaire : ce sera le lieu de tous les possibles, de toutes les premières fois. Et de sa rencontre avec Alaska. La troublante, l'insaisissable Alaska Young, insoumise et fascinante.\n" +
"Amitiés fortes, amour, transgression, quête de sens : un roman qui fait rire, et fondre en larmes l'instant d'après...");
            book_test16.setAuthor("John Green");
            book_test16.setPublisher("Gallimard Jeunesse");
            book_test16.setDateBook(textFormat.parse("2011-03-17"));
            book_test16.setPrice(BigDecimal.valueOf(7.75));
            book_test16.setRating(8);
            book_test16.setLanguageBook("Français");
            book_test16.setStock(105);
            book_test16.setIsbn("978-2070695799");
            book_test16.setHardcover(416);
            book_test16.setNewSelection(false);
            book_test16.setImage("cover-alaska.jpg");
            em.persist(book_test16);
            
            Book book_test17 = new Book();
            book_test17.setCategory(cat_test1);
            book_test17.setTitle("Un sentiment plus fort que la peur");
            book_test17.setDescription("Dans l'épave d'un avion emprisonné sous les glaces du mont Blanc, Suzie Baker retrouve le document qui pourrait rendre justice à sa famille accusée de haute trahison. Mais cette découverte compromettante réveille les réseaux parallèles des services secrets américains. Entraîné par l'énigmatique et fascinante Suzie, Andrew Stilman, grand reporter au New York Times, mène une enquête devenue indispensable à la survie de la jeune femme.");
            book_test17.setAuthor("Marc Levy");
            book_test17.setPublisher("Pocket");
            book_test17.setDateBook(textFormat.parse("2014-04-24"));
            book_test17.setPrice(BigDecimal.valueOf(7.30));
            book_test17.setRating(8);
            book_test17.setLanguageBook("Français");
            book_test17.setStock(10);
            book_test17.setIsbn("978-2266238557");
            book_test17.setHardcover(367);
            book_test17.setNewSelection(true);
            book_test17.setImage("cover-sentiment.jpg");
            em.persist(book_test17);
            
            Book book_test18 = new Book();
            book_test18.setCategory(cat_test3);
            book_test18.setTitle("Wayne Shelton - tome 12 - No return");
            book_test18.setDescription("Dans ce tome 12, Van Hamme et Denayer nous emmènent en Iran sur les pas du plus gentleman des mercenaires : Wayne Shelton ! Ce dernier n'a pas pu profiter longtemps de son argent. L'IRS est passé par là et il doit accepter une mission très dangereuse. Sur l'échiquier mondial de l énergie nucléaire, Shelton risque de n'être qu'un pion dans les mains des services secrets américains et du Mossad israélien. Agents doubles et multiples trahisons, Van Hamme renoue avec les plus grands romans et films d'espionnage !");
            book_test18.setAuthor("Christian Denayer, Jean Van Hamme");
            book_test18.setPublisher("Dargaud");
            book_test18.setDateBook(textFormat.parse("2014-11-28"));
            book_test18.setPrice(BigDecimal.valueOf(11.99));
            book_test18.setRating(10);
            book_test18.setLanguageBook("Français");
            book_test18.setStock(28);
            book_test18.setIsbn("978-2505019404");
            book_test18.setHardcover(56);
            book_test18.setNewSelection(true);
            book_test18.setImage("cover-wayne.jpg");
            em.persist(book_test18);
            
            em.flush();
            
            Customer cust_test1 = new Customer();
            cust_test1.setFirstName("Romain");
            cust_test1.setLastName("Colombo");
            cust_test1.setPasswordHash(PasswordHash.createHash("1234"));
            cust_test1.setEmail("a@b.com");
            cust_test1.setType(2);
            cust_test1.setCity("Paris");
            cust_test1.setCountry("France");
            cust_test1.setStreet1("1 rue blah blah");
            cust_test1.setStreet2("Batiment C");
            cust_test1.setZipCode("75011");
            cust_test1.setTelephone("0123163822");
            em.persist(cust_test1);
            
            
            em.flush();
            
            
            CustomerOrder order_test1 = new CustomerOrder();
            order_test1.setCustomer(cust_test1);
            order_test1.setDateOrder(new Date(78, 10, 1));
            order_test1.setStateOrder(1);
            order_test1.setTotalPrice(BigDecimal.valueOf(12.3));
            em.persist(order_test1);
            
            CustomerOrder order_test2 = new CustomerOrder();
            order_test2.setCustomer(cust_test1);
            order_test2.setDateOrder(new Date(54, 11, 2));
            order_test2.setStateOrder(2);
            order_test2.setTotalPrice(BigDecimal.valueOf(45.70));
            em.persist(order_test2);
            
            CustomerOrder order_test3 = new CustomerOrder();
            order_test3.setCustomer(cust_test1);
            order_test3.setDateOrder(new Date(100, 3, 12));
            order_test3.setStateOrder(3);
            order_test3.setTotalPrice(BigDecimal.valueOf(125.70));
            em.persist(order_test3);
            
            CustomerOrder order_test4 = new CustomerOrder();
            order_test4.setCustomer(cust_test1);
            order_test4.setDateOrder(new Date());
            order_test4.setStateOrder(1);
            order_test4.setTotalPrice(BigDecimal.valueOf(91.05));
            em.persist(order_test4);
            
            em.flush();
            
            OrderLine orderline_test1 = new OrderLine();
            orderline_test1.setOrderLinePK(new OrderLinePK(order_test1.getId(), book_test1.getId()));
            orderline_test1.setQuantity(123);
            em.persist(orderline_test1);
            

            ut.commit();

            
        } catch (NotSupportedException | SystemException | ParseException | NoSuchAlgorithmException | 
                InvalidKeySpecException | RollbackException | HeuristicMixedException | 
                HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            
            Logger.getLogger(InstallBean.class.getName()).log(Level.SEVERE, null, ex);
            return "Nok";
        }
        
        return "ok";
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
}
