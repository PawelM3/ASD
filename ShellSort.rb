class ShellSort
  require 'benchmark'

  def self.shell_sort(array_to_sort, gaps, to_print)
    sorted_size = array_to_sort.size # rozmiar sortowanej tablicy
    gaps.each do |gap|
      if sorted_size >= gap
        for i in gap...sorted_size
          j = i
          while j >= gap
            if array_to_sort[j - gap] > array_to_sort[j]
              temp = array_to_sort[j]
              array_to_sort[j] = array_to_sort[j - gap]
              array_to_sort[j - gap] = temp
            end
            j -= gap
          end
        end
      end
      if to_print
        print 'Po sortowaniu z krokiem ', gap, ': ', array_to_sort, "\n"
      end
    end
  end

#zestaw przeskoków według danych Wikipedii https://en.wikipedia.org/wiki/Shellsort
  gaps_ciura = [701, 301, 132, 57, 23, 10, 4, 1] #Ciura
  gaps_wiki = [5, 3, 1] #Wikipedia Test
  gaps_tokuda = [103, 46, 20, 9, 4, 1] #Tokuda
  gaps_sedgewick_a = [109, 41, 19, 5, 1] #Sedgewick A
  gaps_sedgewick_b = [281, 77, 23, 8, 1] #Sedgewick B
  gaps_incerpi = [112, 48, 21, 7, 3, 1] # Incerpi et al
  gaps_pratt_a = [121, 40, 13, 4, 1] # Pratt A
  gaps_pratt_b = [12, 9, 8, 6, 4, 3, 2, 1] # Pratt B
  gaps_pepernov = [65, 33, 17, 9, 5, 3, 1] #Pepernov et al
  gaps_hibbard = [63, 31, 15, 7, 3, 1] #Hibbard
  array_size = 10000;
  numbers_to = 10000;
  generated_array = Array.new(array_size) {Random.rand(1...numbers_to)}
  wiki_array = [62, 83, 18, 53, 7, 17, 95, 86, 47, 69, 25, 28]
  print 'Demonstracja algorytmu na danych z Wikipedii', "\n" #https://en.wikipedia.org/wiki/Shellsort
  print 'Tablica początkowa: '
  print wiki_array, "\n"
  print 'Sortujemy:', "\n"
  shell_sort(wiki_array, gaps_wiki, true)
  print 'Tablica posortowana powyżej', "\n", "\n"
  print 'Benchmark różnych proponowanych skoków na tablicy ', array_size, ' elementowej, złożonej z liczb od 1 do ', numbers_to, "\n"
  Benchmark.bm(20) do |bm|
    array_for_sort = generated_array.map do |e|
      e.dup
    end #głębokie kopiowanie tablicy
    bm.report("Ciura") {
      shell_sort(array_for_sort, gaps_ciura, false)
    }
    array_for_sort = generated_array.map do |e|
      e.dup
    end
    bm.report("Wiki") {
      shell_sort(array_for_sort, gaps_wiki, false)
    }
    array_for_sort = generated_array.map do |e|
      e.dup
    end
    bm.report("Tokuda") {
      shell_sort(array_for_sort, gaps_tokuda, false)
    }
    array_for_sort = generated_array.map do |e|
      e.dup
    end
    bm.report("Sedgewick A") {
      shell_sort(array_for_sort, gaps_sedgewick_a, false)
    }
    array_for_sort = generated_array.map do |e|
      e.dup
    end
    bm.report("Sedgewick B") {
      shell_sort(array_for_sort, gaps_sedgewick_b, false)
    }
    array_for_sort = generated_array.map do |e|
      e.dup
    end
    bm.report("Incerpi") {
      shell_sort(array_for_sort, gaps_incerpi, false)
    }
    array_for_sort = generated_array.map do |e|
      e.dup
    end
    bm.report("Pratt A") {
      shell_sort(array_for_sort, gaps_pratt_a, false)
    }
    array_for_sort = generated_array.map do |e|
      e.dup
    end
    bm.report("Pratt B") {
      shell_sort(array_for_sort, gaps_pratt_b, false)
    }
    array_for_sort = generated_array.map do |e|
      e.dup
    end
    bm.report("Pepernov") {
      shell_sort(array_for_sort, gaps_pepernov, false)
    }
    array_for_sort = generated_array.map do |e|
      e.dup
    end
    bm.report("Hibbard") {
      shell_sort(array_for_sort, gaps_hibbard, false)
    }
  end
end
