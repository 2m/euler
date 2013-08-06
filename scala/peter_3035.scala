/**
 * 3035 metai. Kosmoso kaljoklis Petras ruošiasi paskutinei kelionei iš
 * Planetos X į Žemę, kur planuoja išeiti į pensiją. Jo tikslas nėra grįžti
 * namo kuo greičiau, tačiau priešingai: Petras nori kiek įmano ilgiau
 * pasigrožėtį žvaigždėmis ir galbūt dar neaplankytais pasauliais. Todėl
 * jis nori pasirinkti patį patraukliausią jam maršrutą (ir tuo pačiu
 * išvengti išsilaipinimo nedraugiškose planetose), tačiau nežino, kaip tai
 * padaryti. Padėkite jam.
 *
 * Tekstiniame faile duoti visi reikalingi duomenys:
 * Pirmoje eilutėje įrašytas planetų skaičius N
 * Kitoje eilutėje yra N skaičių (nuo 0 iki 10), kur kiekvienas i-tasis
 * skaičius reiškia i-tosios planetos patrauklumą (0 reiškia, jog tai yra
 * nedraugiška planeta). Planetos X numeris - 0, Žemės numeris - N-1.
 * Trečioje eilutėje įrašytas galimų skrydžių tarp planetų porų skaičius M.
 * Sekančiose M eilučių duota informacija apie pačius skrydžius: planetų
 * pora (jų numeriai) ir skrydžio patrauklumo rodiklis nuo 1 iki 10.
 *
 * Duomenų failo pavyzdys
 *
 * 4
 * 10 5 0 10
 * 5
 * 0 3 2
 * 0 1 5
 * 1 2 10
 * 2 3 1
 *
 * Sudarykite patraukliausio skrydžio planą Petrui turint galvoje tai, jog
 * ne visose planetose Petras norėtų išsilaipinti. Taip pat jis žino,
 * niekur galaktikoje negali skristi ratu. Rezultatuose aiškiai turi matytis
 * skrydžio planas su išsilaipinimais ir gauta patrauklumo rodiklio reikšmė.
 *
 * Taip pat atsiminkite, jog galaktika didelė, o Petras turi užtektinai
 * kuro tokiai kelionei namo ir Petras tikrai žino, jog bent vienas
 * maršrutas namo egzistuoja.
 */

/*val edgeRewards = List(10, 5, 0, 10)
val vertices = List(
  (0, 3, 2),
  (0, 1, 5),
  (1, 2, 10),
  (2, 3, 1)
)*/

// vertices taken from http://optlab-server.sce.carleton.ca/POAnimations2007/DijkstrasAlgo.html
// edge rewards - from head
val edgeRewards = List(10, 4, 5, 3, 1, 2, 6, 7)
val vertices = List(
  (0, 1, 2),
  (0, 2, 5),
  (0, 3, 4),

  (1, 2, 2),
  (1, 4, 7),
  (1, 6, 12),

  (2, 3, 1),
  (2, 4, 4),
  (2, 5, 3),

  (3, 5, 4),
  (4, 5, 1),
  (4, 7, 5),
  (5, 7, 7),
  (6, 7, 3)
)

val startNode = 0
val endNode = edgeRewards.length - 1

val graph = vertices.flatMap { case(from, to, value) => List((from, to, value, edgeRewards(to)), (to, from, value, edgeRewards(from))) }

val tentativeReward = Array.fill(graph.map(_._1).max + 1)(-1)
tentativeReward(0) = 0

var unvisitedNodes = graph.map(_._1).filter(_ > 0).distinct

var currentNode = startNode
var done = false
while (!done) {
  println("Going to node " + currentNode)

  graph.
    filter { case(from: Int, to: Int, value: Int, edgeReward: Int) => from == currentNode }. // get current node neighbours
    filter { case(from: Int, to: Int, value: Int, edgeReward: Int) => unvisitedNodes.contains(to) }. // get only unvisited nodes
    map { case(from: Int, to: Int, value: Int, edgeReward: Int) => (to, tentativeReward(from) + value + edgeReward) }. // map nodes to tentative rewards
    foreach { case(node: Int, reward: Int) =>
      if (reward > tentativeReward(node)) {
        tentativeReward(node) = reward
      }
    }

  unvisitedNodes = unvisitedNodes.filter(_ != currentNode) // mark current node as visited

  if (currentNode != endNode) {
    val unvisitedNodesWithRewards = tentativeReward.zipWithIndex.filter { case(reward: Int, node: Int) => unvisitedNodes.contains(node) }
    if (unvisitedNodesWithRewards.nonEmpty) {
      val maxRewardNode = unvisitedNodesWithRewards.maxBy(_._1)
      if (maxRewardNode._1 != -1) {
        currentNode = maxRewardNode._2
      }
      else {
        // no more paths to unvisited nodes
        done = true
      }
    }
    else {
      // no more unvisited nodes
      done = true
    }
  }
  else {
    // stepped on end node
    done = true
  }
}

println("Total reward " + (edgeRewards(startNode) + tentativeReward(endNode)));