#!/bin/bash

if [[ "$#" -lt "2" ]]; then
	echo "Usage:"
	echo "$0 [.jar file to test] [test data directory] [-f]"
	echo "Parameters:"
	echo "-f	Force running tests (no skipping when expected output not found)"
	echo "Example: $0 KillerSokoban.jar testdata"

	exit 2 # exitcode 2 means wrong parameters
fi

BINARY="$1"
TEST_DATA="$2"

if [[ ! -f "$1" ]]; then
	echo ".jar not exists"
	exit 2 # exitcode 2 means wrong parameters
fi

if [[ ! -d "$2" ]]; then
	echo "test case directory not exists"
	exit 2 # exitcode 2 means wrong parameters
fi

success="0"
total="0"
crashed="0"

start=$(date +%s)

for t in $TEST_DATA/*.testcase; do

	tput sgr0
	echo -e "\n-----------------------"
	tput bold
	echo "Running: $t..."
	tput sgr0

	total=$(( $total + 1 ))

	EXPECTED="$t.expected"

	if [[ -f "$EXPECTED" ]]; then

#		$BINARY < "$t" | diff - "$EXPECTED" # ha nem java-t tesztelunk
		java -jar "$BINARY" < "$t" | diff - "$EXPECTED" # actual test

		result=("${PIPESTATUS[@]}")

		if [[ "${result[0]}" -ne "0" ]]; then
			tput setaf 1
			echo "PROGRAM CRASHED!"
			echo "TEST FAILED!"
			crashed=$(( $crashed + 1 ))
			continue
		fi

		if [[ "${result[1]}" -ne "0" ]]; then
			tput setaf 1
			echo "TEST FAILED!"
		else
			tput setaf 2
			echo "TEST SUCCESSFUL!"
			success=$(( $success + 1 ))
		fi


	else
		tput setaf 3
		if [[ "$3" == "-f" ]]; then
			echo "WARNING: No expected output specified. Running test anyways." # but marking it as failed
			tput sgr0 # a warning sarga, de a teszt ne legyen az

#			$BINARY < "$t" # actual test, ha nem java-t tesztelunk
			java -jar "$BINARY" < "$t" # actual test
		else
			echo "WARNING: No expected output specified. Skipping test." # and marking it as failed
		fi

	fi

done

end=$(date +%s)

tput sgr0
echo -e  "\n-----------------------"

[[ "$total" -ne "$success" ]] && tput setaf 1 || tput setaf 2 # coloring
echo "Done!"
echo "Running tests took $(( end - start )) seconds"
echo "Total tests run: $total"
echo "Successful tests: $success"
[[ "$crashed" -eq "0" ]] && echo "Failed tests $(( $total - $success ))" || echo "Failed tests $(( $total - $success )) ($crashed crashed)"
[[ "$total" -eq "$success" ]] && echo "Complete success!!" || echo "This sad :("

tput sgr0
echo "-----------------------"

[[ "$total" -ne "$success" ]] && exit 1 # exitcode 1 indicates failure in tests
