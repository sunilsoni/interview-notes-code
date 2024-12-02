package com.interview.notes.code.year.y2023.june23.test6;

import java.util.Scanner;

public class StockProfit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the prices of the stocks.

        int[] prices = {997845, 4073139, 4683946, 9293097, 6476800, 5014265, 998322, 4810954, 4229262, 9198751, 4563706, 1881248, 3156390, 7773039, 5231735, 5481677, 433055, 3013715, 4196102, 7821439, 9146473, 7684897, 1768867, 5298530, 177953, 8973457, 2322806, 1728711, 5203985, 6974427, 8720402, 6086708, 7630913, 4101859, 4663623, 6220019, 2417197, 6809918, 4877206, 3150618, 8779001, 7263816, 4838385, 9952120, 5833143, 8980620, 4998697, 7096064, 6638669, 9288877, 5912318, 2490383, 8437499, 9094328, 6549965, 3120978, 7390884, 2425560, 7602917, 1181379, 9652182, 9332816, 5750263, 7430598, 9766971, 9941338, 4623031, 3355079, 1690545, 9555598, 5768429, 7519845, 3650329, 5608783, 2138935, 9093973, 921919, 6103517, 610278, 2055342, 3641243, 6717462, 4664484, 7918484, 7640061, 6054036, 5084455, 2720232, 9325131, 9644548, 2418090, 3975790, 7982152, 689858, 5144688, 6401085, 6232034, 9095564, 5194486, 7942038, 3536266, 4978770, 8933072, 5305185, 1387691, 2657221, 6261309, 2295351, 6582593, 1796669, 1707176, 1786616, 7475246, 5457023, 5698658, 9592785, 2990241, 2818646, 1827501, 135075, 7710830, 6958743, 5409309, 2176789, 3402479, 4199023, 8501212, 1534429, 569509, 3523649, 2329061, 1627763, 5391023, 8501897, 5052255, 9252096, 2950775, 3271100, 7435291, 7495719, 6067485, 8030929, 6146709, 7038816, 7779640, 8299874, 2743710, 3249113, 1928497, 574912, 4897845, 6733850, 9452581, 5583101, 5257679, 4408281, 6500212, 3174423, 8845980, 831529, 4774203, 9442844, 5239316, 1536680, 5165405, 2799404, 4362002, 2121979, 4429781, 5996088, 1754337, 9881625, 673727, 4765502, 2908845, 1632926, 5500511, 380436, 2705276, 621054, 2885745, 2305801, 416842, 6148689, 1246712, 5298921, 4973080, 7647290, 7336403, 7529319, 2404366, 522596, 518589, 4532182, 4104265, 4273064, 9939386, 946780, 4021035, 1317842, 1620015, 8565360, 9123643, 2860209, 6506860, 2330899, 4053400, 5867538, 5545406, 3991487, 1073733, 9110245, 1529012, 9439902, 3097388, 6822376, 2002440, 7015707, 2106981, 5339624, 6200888, 9745036, 423188, 1595543, 3164917, 9806901, 9731072, 5998688, 1063503, 9187352, 1542632, 3924619, 2528961, 6925894, 6519323, 2960937, 1388742, 632938, 5374660, 6334868, 3595518, 130754, 7301068, 5566671, 3266327, 5695390, 3126520, 9003991, 4116664, 7701158, 9204597, 1271828, 3471320, 368569, 8794176, 8115679, 7070738, 891053, 2091526, 6042370, 3812304, 5911904, 9167258, 7864280, 7451016, 1762287, 4672874, 4055042, 7797319, 7192683, 1017624, 4957917, 8102671, 4095258, 9932360, 6428573, 3327809, 7258108, 3080461, 7498285, 6985532, 412953, 1460084, 6526729, 8843421, 1989045, 5862418, 7024584, 3115265, 2619103, 769343, 9801135, 7190843, 1360473, 7604615, 2252674, 175787, 4600804, 3940873, 6443826, 2190280, 1576708, 6908410, 3550956, 5505002, 6111722, 4633247, 8021732, 9221472, 7799210, 3075740, 5025539, 7143773, 7683807, 6938026, 4881625, 6093186, 7106887, 481365, 9594686, 4286895, 3018178, 994208, 8912326, 7126642, 2964364, 2353805, 5288527, 4221864, 6965051, 4972861, 8804557, 1242973, 3967546, 7613528, 3139898, 8632674, 8653844, 2700308, 3484831, 8734139, 5627196, 2546360, 8511066, 2220580, 406700, 7927853, 6563460, 9713943, 5296956, 9719255, 6764754, 8229023, 5641812, 1154853, 7193797, 7653858, 9795451, 1128276, 4497268, 2867316, 6171960, 145688, 8675665, 9976322, 9049071, 179106, 570354, 5069330, 1814044, 4520308, 8492858, 6038314, 6533586, 6639114, 1456205, 5622678, 3317724, 4306219, 8170859, 7748611, 4931872, 1294080, 5010390, 2851521, 9637111, 6886726, 5292922, 7164463, 8884056, 5822140, 4379218, 694510, 7429275, 2280677, 2691033, 7395220, 2821445, 7735967, 5032147, 1663224, 8195046, 6602002, 4302062, 8372663, 992602, 3279257, 8536213, 2901536, 5714517, 1929359, 5521923, 9647814, 3914085, 2662628, 3547740, 1690228, 4894844, 2922812, 717819, 6428923, 3170825, 7542435, 2491738, 2016563, 2607738, 1544217, 3885593, 158775, 9336356, 5016144, 2976909, 8411270, 1410859, 2977284, 2025167, 9407456, 2903289, 4235356, 4645259, 1717475, 5616101, 6465915, 7281609, 7664302, 1839422, 806850, 4440745, 1094790, 8164641, 7748097, 3818856, 6400513, 709585, 5132729, 786808, 7932620, 9281297, 4327488, 4922635, 5898387, 4011139, 7051519, 6611793, 2539555, 6028106, 2874119, 1110542, 4041608, 7029531, 742622, 9210211, 6593821, 5230590, 7306584, 6140778, 3197419, 7232285, 1593323, 1241331, 9741623, 5364641, 6318669, 8608408, 4762669, 7216493, 7411452, 5732564, 9504132, 176114, 5529469, 1480446, 4914473, 5119036, 3197401, 505058, 9481323, 8578999, 8440065, 9015466, 6193931, 5788499, 5361423, 4402047, 4237591, 8065296, 9646948, 5512552, 3548594, 517104, 8683898, 2019806, 5891014, 7754230, 8137013, 9740880, 530369, 5276703, 1559181, 7384703, 9465165, 6729648, 3181263, 1145715, 7957414, 2433869, 7352017, 7309735, 3772722, 7301689, 258586, 5806957, 7588998, 8559591, 2731417, 1597453, 7223347, 2325759, 9276204, 326052, 8968527, 703101, 347010, 2696003, 1699509, 6319727, 2634974, 7035826, 8299067, 127561, 3296546, 8393987, 3380350, 4359849, 214962, 7594619, 2499132, 9030509, 1580028, 8973468, 9422854, 5435025, 7128078, 4434260, 140523, 9889579, 3236044, 7920357, 8040170, 7625290, 1057519, 893337, 4770152, 4857268, 3562626, 9648514, 6321826, 4410563, 9319111, 2893472, 8671359, 4886904, 4401742, 3096498, 1923996, 815969, 6428169, 7882306, 8690694, 3792946, 776879, 6536829, 7546562, 161104, 3784241, 8421405, 8952747, 3477289, 278366, 9524187, 4545107, 2825904, 1031465, 1142854, 8374047, 1865826, 8942475, 8639056, 7612307, 3442089, 2725290, 122234, 4727535, 8970538, 4312740, 3556907, 388617, 7990662, 5624649, 7687696, 7978075, 6383530, 9833868, 8422421, 5958900, 3758748, 6005832, 1594815, 7586674, 669241, 6432238, 1643230, 3294948, 740964, 6426177, 6685449, 3016689, 1023156, 5858514, 1568911, 9171250, 457157, 1591778, 7215660, 3904925, 5838755, 5450080, 3625516, 9466134, 6457882, 7951443, 3604739, 2732801, 7131128, 165617, 2614888, 5638173, 8299687, 987486, 7917911, 5011137, 6951592, 6528453, 5276346, 9348476, 1493966, 407270, 9054320, 6673049, 6242325, 7004911, 3774827, 5983738, 1938999, 3129252, 8228221, 8087279, 3717993, 2143217, 3070965, 1161886, 7131064, 3577377, 6173132, 7782318, 9565295, 5378557, 6398008, 8753145, 2880109, 4074917, 130682, 1578556, 1957124, 9277882, 2025214, 2385272, 3524096, 9908978, 8323793, 2811102, 1684571, 4406308, 488747, 7680381, 4303069, 2144878, 187222, 6335408, 1642977, 9708554, 210420, 4341059, 2412581, 1504633, 2429541, 8832250, 9153962, 8476924, 5434020, 1799186, 4610357, 9018677, 7213837, 4551321, 8390048, 1051269, 8260877, 9507174, 4470027, 4265501, 6086720, 5245352, 9076413, 6950721, 3439526, 9688965, 8042880, 1417156, 9305697, 3348304, 976903, 5636675, 2793094, 1158179, 1765105, 2381425, 480084, 7302371, 7042546, 3269166, 6015100, 6063038, 5107125, 2959954, 8256288, 171562, 9669925, 2330010, 2118798, 2869836, 286892, 8890968, 2919459, 1484231, 8063117, 2354589, 3926798, 3053226, 9855092, 8014883, 4303481, 3418643, 9227704, 8755903, 8697907, 3504119, 8783896, 2035388, 8736974, 6534515, 8446650, 6093979, 921241, 2302360, 7964614, 170989, 110437, 2001608, 2093835, 9123589, 3844035, 9894576, 7235771, 4579653, 674645, 2587081, 118573, 5343278, 5849995, 3315557, 488786, 3473503, 1826352, 8552794, 3371209, 2212113, 2248469, 317967, 4362674, 3519138, 8004847, 786480, 8070386, 2887207, 2097873, 9337765, 497619, 7074012, 8098088, 8205152, 6725226, 1631433, 7461615, 384556, 6683590, 2773217, 9942583, 4215767, 3455359, 857238, 8323454, 2391219, 8188970, 2857097, 7951080, 7313004, 9141832, 8320539, 1497671, 9041551, 5706834, 3331851, 6365706, 9046205, 8101134, 8472914, 935992, 9931583, 1329190, 8276600, 131988, 5592996, 2824466, 1581111, 7419136, 4496701, 9102211, 4986795, 2646107, 312065, 9452815, 9280783, 4682928, 9424746, 3192786, 8799973, 2640616, 1484481, 4053314, 710416, 267730, 6949369, 8940038, 5328538, 167346, 6877942, 9104913, 7209482, 8443437, 7619582, 5866168, 1338077, 1564878, 9017603, 1777330, 1052814, 1461320, 9198582, 9413388, 1703304, 173950, 6325072, 3024465, 3953339, 3472219, 3277285, 9242534, 8649095, 4250974, 9520838, 6492221, 8622018, 9787952, 9339140, 264070, 4691226, 4497539, 8349096, 4980339, 4104114, 3571030, 5182151, 9583838, 1907711, 6829445, 7982353, 5639948, 2115903, 2485889, 133518, 8523007, 8603157, 6491930, 7523676, 173144, 8633355, 333885, 2629741, 4972239, 8842689, 3686199, 7536404, 683899, 6038917, 6254240, 9058301, 2916216, 7046194, 7086903, 3965451, 6821218, 649992, 9481260, 2566895, 9647795, 7518365, 8885625, 1193073, 6453123, 4194565, 2359955, 397790, 2111009, 2272177, 7590711, 512373, 6824911, 8191063, 3794944, 8722784, 4844167, 6515500, 9220084, 1211260, 2309367, 4288706, 766816, 7861476, 1618742, 4720971, 3805755, 7223124, 1690835, 9324962, 8200639, 5437128, 5797873, 2154158, 7622373, 745296, 3598772, 1431592, 3913303, 5342092, 2359629, 5308523, 5230478, 2836332, 3319524, 1710692, 1086757, 6582433, 3605428, 6177365, 3201043, 8631811, 3525392, 9213986, 1110001, 6563440, 4014572, 6524039, 6170374, 3760966, 9251752, 4464234, 1586992, 2684865, 9093061, 2978080, 5161421, 1127041, 7021040, 6999317, 2998221, 2450943, 6320502, 5236131, 1053390, 1292429, 2129372, 3092994, 4930223, 4233334, 8113955, 4294406, 1363069, 2692175, 5110819, 9615841, 2924936, 721988, 4244976, 6596122, 517651, 5665390, 6371282, 7310897, 4668257, 3454345, 2709067, 2257012, 7952667, 901606, 9679709, 8576943, 685277, 8013543, 9908692, 5576448, 1656613, 7958270, 8938853, 1371215, 421266, 3060909, 9660853, 6087864, 1196940, 4156318, 5985031, 5782455, 2452346, 5765776, 5247327, 9845942, 7099030, 836822, 2732674, 282893, 4542581, 1329886, 3731698, 7798810, 5579940, 5297824, 9635245, 6545152, 4402732, 7470850, 4075817, 6523326, 7066790, 9415437, 8103031, 2602433, 2853642, 3693524, 9959014, 1495634, 6082210, 6421514, 2515927, 1675323, 4816376, 4940673, 9437745, 7136726, 4543014, 4215830, 1465336, 9702361, 6083462, 1393821, 6409157, 930132, 8813368, 6502765, 4229720, 9011224, 4894028, 5329459, 9320616, 619465, 1689846, 5720117, 1181553, 3010546, 6709183, 290736, 283864, 4086338, 1926455, 8592061, 5629618, 867355, 2960000, 7019368, 3560409, 7986543, 8273905, 2687545, 6554563, 7683216, 5265895, 4676524, 3012490, 6977436, 9710926, 4619155, 3371605, 1805659, 2823911, 8063707, 2820042, 1034515, 5240729, 519540, 7107751, 18};

        // Find the maximum profit that can be made.
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                maxProfit = Math.max(maxProfit, prices[i + 1] - prices[i]);
            }
        }

        // Print the maximum profit.
        System.out.println(maxProfit);
    }
}
