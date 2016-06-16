
package alpencolor;

/**
 *Contains the prices of AlpenColor Products
 *@author tobiasgozzi
 */
public enum Prices {
    KOMPAKT_COLOR_CONVERTER(10.14,5.07,"Kompakt Color Converter"),
    KOMPAKT_COLOR_WEISS(10.14,5.07,"Kompakt Color Weiss"),
    PUR_COLOR_CONVERTER(7.0,3.50,"PUR Color Converter"),
    PUR_COLOR_WEISS(7.1,3.55,"PUR Color Weiss"),
    PUR_HOCHGLANZ_CONVERTER(7.86,3.93,"PUR Hochglanz Converter"),
    PUR_HOCHGLANZ_WEISS(7.86,3.93,"PUR Hochglanz Weiss"),
    PU82063_ARANCIO_CHIARO(60.86,30.43,"PU82063 arancio chiaro"),
    PU82555_ARANCIO_SCURO(85.7,42.85,"PU82555 arancio scuro"),
    PU82600_BIANCO(10.56,5.28,"PU82600 bianco"),
    PU82504_BLU_VERDE(32.92,16.46,"PU82504_blu_verde"),
    PU82556_BLU_ROSSO(30.84,15.42,"PU82556_blu_rosso"),
    PU82010_GIALLO(62.3,31.15,"PU82010_giallo"),
    PU82263_GIALLO_LIMONE(76.8,38.4,"PU82263_giallo_limone"),
    PU82507_GIALLO_OSSIDO(19.04,9.52,"PU82507_giallo_ossido"),
    PU82551_GIALLO_CHIARO(66.04,33.02,"PU82551_giallo_chiaro"),
    PU82515_GIALLO_CALDO(46.36,23.18,"PU82515_giallo_caldo"),
    PU82590_MAGENTA(66.04,33.02,"PU82590_magenta"),
    PU82597_NERO(21.52,10.76,"PU82597_nero"),
    PU82508_ROSSO_SCURO(30.42,15.21,"PU82508_rosso_scuro"),
    PU82512_ROSSO_OSSIDO(20.5,10.25,"PU82512_rosso_ossido"),
    PU82557_ROSSO_VIVO(67.06,33.53,"PU82557_rosso_vivo"),
    PU82536_VERDE(31.88,15.94,"PU82536_verde"),
    PU82541_VIOLA(67.06,33.53,"PU82541_viola"),
    PU82545_VIOLETTO(73.28,35.64,"PU82545_violetto"),
    PU82628_ALLUMINIO(49.48,24.74,"PU82628_alluminio"),
    PU82629_ROSSO_OX_TR(65.20,32.60,"PU82629_rosso_ox_tr"),
    PU82630_ALLUMINIO_GROSSO(78.46,39.23,"PU82630_alluminio_grosso"),
    PU82631_ALLUMINIO_FINE(25.66,12.83,"PU82631_alluminio_fine"),
    PU82632_GIALLO_OX_TR(58.78,29.39,"PU82632_Giallo_ox_tr"),
    GF1(4.06,1.45,"GF1"),
    GF2(4.06,1.45,"GF2"),
    GF3(4.06,1.45,"GF3");


    final public double EP;
    final public double VK;
    final public String PROD_NAME;
    
    Prices(double VK,double EP, String NAME){
        this.PROD_NAME = NAME;
        this.EP = EP;
        this.VK = VK;
    }
}
