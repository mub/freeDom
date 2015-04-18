#!/bin/env ruby
require 'Open3'
puts "Param: #{$*.join(' ')}"

USER_HOME = ENV['USERPROFILE'] || ENV['HOME']

puts USER_HOME

M2_REPO = File.join(USER_HOME, '.m2', 'repository')

p Dir.entries(M2_REPO)

o = %x{java -jar target/freeDomParser-1.0.0-jar-with-dependencies.jar #{$*.join(' ')}}
p o
