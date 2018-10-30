

/*
 * Copyright (c) 2018. Created by mrocigno
 *                                     _
 *                                    /_\
 *                       _            )_(            _
 *                       |`-.___,.-~'`|=|`'~-.,___,-'|
 *                       |  __________|=|__________  |
 *                       | |          |=|          | |
 *                       | |          |=|          | |
 *                       | |          |=|          | |
 *                       | |        ,-|_|-.        | |
 *                       | |      ,' _____ ',      | |
 *                       | |     / ,'| A |'. \     | |
 *                       | |    /_// |/V\| \\_\    | |
 *            ___________|_|_________|___|_________|_|__________
 *           |             ____            _                    |
 *           |   _ __ ___ |  _ \ ___   ___(_) __ _ _ __   ___   |
 *           |  | '_ ` _ \| |_) / _ \ / __| |/ _` | '_ \ / _ \  |
 *           |  | | | | | |  _ < (_) | (__| | (_| | | | | (_) | |
 *           |  |_| |_| |_|_| \_\___/ \___|_|\__, |_| |_|\___/  |
 *           |                                |___/             |
 *           '--------------------------------------------------'
 *                       \ \         | | |         / /
 *                        \ \        | | |        / /
 *                         \ \       | | |       / /
 *                          `.`.     | | |     ,','
 *                            `.`.   | | |   ,','
 *                              `.`-.| | |,-','
 *                                `-.| | |,-'
 *                                   | | |
 *                                   | | |
 *                                   | | |
 *                                   | | |
 *                                   | | |
 *                                    \|/
 *                                     V
 */

package lib.rocigno.usefulthingslib.Utils;

import java.util.Random;

public class LoremIpsum {
    private static String defLI =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla tortor nulla, euismod in urna quis, varius consectetur quam. Nam pellentesque diam id dolor tincidunt, a sagittis turpis tempor. Duis ac pulvinar ante. Donec velit nisl, faucibus at fermentum et, tempus et odio. Maecenas pulvinar augue turpis, quis tempus justo bibendum at. Ut massa nulla, tempor quis ex eu, bibendum blandit arcu. Maecenas id viverra enim. Suspendisse rutrum vehicula ultricies. Aliquam id ultricies dui, non malesuada lectus. Duis lobortis quam nec ipsum suscipit aliquam. Quisque non dictum elit. Cras sed malesuada arcu, facilisis gravida nulla. Aenean eget sagittis tortor, ac imperdiet risus. Donec vulputate molestie neque, non rhoncus libero cursus ac. Nullam odio nibh, euismod non ex ac, volutpat consequat velit. Maecenas sed metus egestas, dapibus ex vitae, molestie mauris. " +
            "Vestibulum porta erat quis lacus ullamcorper sodales. Sed sollicitudin ornare elit, eget venenatis elit euismod hendrerit. Curabitur id justo non nisl faucibus maximus. Aliquam ut sem in eros pharetra dapibus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam vitae mauris eget ex faucibus ullamcorper sed vestibulum neque. Duis id urna lectus. Suspendisse faucibus tortor ut congue vestibulum. Sed viverra diam augue, nec eleifend nunc sagittis a. Maecenas vitae elit eu mauris luctus placerat. Donec auctor enim eu urna sollicitudin, commodo vehicula ex commodo. Donec vitae molestie arcu. Nunc velit erat, egestas in diam non, auctor posuere sem. " +
            "Sed consequat tempor orci, at fermentum erat rhoncus at. Duis justo mi, fringilla quis lobortis nec, consectetur sed enim. Integer augue magna, dignissim non rhoncus eu, viverra vel neque. Donec volutpat consequat dui, id varius tellus pharetra a. Donec et neque lobortis, lacinia urna non, vulputate purus. Donec sed viverra sapien. Nullam vitae mollis lectus, ac ultricies libero. Phasellus vestibulum sapien dui. Cras nulla mi, mattis ac orci eget, lacinia commodo eros. Nulla facilisi. Aenean maximus, nisl sed iaculis semper, leo nibh congue arcu, sit amet varius augue erat a quam. Phasellus auctor arcu ut feugiat molestie. Proin eu diam vel mauris lobortis ultrices id in turpis. Nunc ut lectus eu nibh fermentum consectetur. Proin dui tellus, pellentesque sed arcu dignissim, semper aliquet sem. " +
            "Maecenas maximus nisi vitae luctus pharetra. Sed urna turpis, tincidunt at neque quis, aliquam lacinia augue. Phasellus ornare sed justo at mattis. Sed imperdiet odio semper efficitur lobortis. Cras dictum maximus ultrices. Morbi luctus posuere fringilla. Morbi auctor lorem in arcu fringilla, vitae laoreet risus malesuada. Phasellus consectetur pulvinar ullamcorper. Suspendisse lacinia velit felis, non iaculis lectus pretium ac. Nulla vitae nisl sodales, accumsan est sit amet, vulputate sem. Nulla commodo, velit nec ullamcorper tristique, risus purus rhoncus leo, et consequat arcu purus et magna. Pellentesque in enim euismod, faucibus massa at, accumsan mi. Morbi sit amet libero eget nisi congue facilisis. Proin convallis vitae justo quis dictum. " +
            "Vestibulum pretium posuere mi, in rhoncus metus viverra eu. Cras nec massa hendrerit, mattis ante at, dapibus risus. Nulla dictum egestas elit vitae laoreet. Fusce nibh lectus, placerat at felis sed, porttitor sollicitudin mi. Quisque id nunc in sem commodo interdum. Vivamus feugiat egestas est at tincidunt. Cras sed risus felis. Praesent condimentum eleifend dolor, vel bibendum sapien. Aliquam vitae consectetur dui. Aenean ligula dolor, maximus at accumsan nec, fermentum euismod est. Vivamus at diam enim. Ut malesuada at arcu ornare rutrum. Cras quis orci arcu. Maecenas sed tristique purus.";

    public static String create(int numWords){
        String result = "Lorem ipsum ";

        String[] divider = defLI.split(" ");
        for (int i = 0; i < numWords - 2; i++) {
            String vle = divider[new Random().nextInt(divider.length)];
            if(result.substring(result.length() - 2).equals(". ")){
                vle = vle.substring(0,1).toUpperCase().concat(vle.substring(1));
            }else{
                vle = vle.substring(0,1).toLowerCase().concat(vle.substring(1));
            }
            result += vle + " ";
        }

        return result;
    }
}
