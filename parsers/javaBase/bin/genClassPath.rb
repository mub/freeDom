require 'open3'

=begin
This is preserved for historical reference, run the omni-jar instead.
=end

TGT = 'bin/localClassPath.txt'

puts 'Building CLASSPATH ...'

o, e, s, = Open3.capture3('mvn dependency:build-classpath')

if s.exitstatus != 0
    $stderr.puts %|ERROR: #{s.inspect}
#{e}|

    puts o
    exit 1
end

isNext = false

o.split("\n").each { |rawLine|
    line = rawLine.strip
    if isNext
        IO.write(TGT, line.gsub('C:\\', '/').gsub('\\', '/'), {mode: 'wb'})
        puts %| CLASSPATH saved to #{TGT}
|
        exit 0
    end
    next unless line.start_with?('[INFO] Dependencies classpath:')
    isNext = true
}

$stderr.puts %|Classpath not found in:
#{o}
|

exit 2
