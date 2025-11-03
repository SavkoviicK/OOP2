package zad3;

import java.util.Random;

public enum Potez {
    KAMEN, PAPIR, MAKAZE, GUSTER, SPOK;

    public static Potez parse(String s) {
        if (s == null) return null;
        String x = s.trim().toUpperCase();
        switch (x) {
            case "K": case "KAMEN": return KAMEN;
            case "P": case "PAPIR": return PAPIR;
            case "M": case "MAKAZE": return MAKAZE;
            case "G": case "GUSTER": return GUSTER;
            case "S": case "SPOK": case "SPOCK": return SPOK;
            default: return null;
        }
    }

    public static Potez random(Random r) {
        return values()[ r.nextInt(values().length) ];
    }

    public boolean beats(Potez o) {
        switch (this) {
            case KAMEN:   return o == MAKAZE || o == GUSTER;
            case PAPIR:   return o == KAMEN  || o == SPOK;
            case MAKAZE:  return o == PAPIR  || o == GUSTER;
            case GUSTER:  return o == PAPIR  || o == SPOK;
            case SPOK:    return o == KAMEN  || o == MAKAZE;
            default:      return false;
        }
    }
}
