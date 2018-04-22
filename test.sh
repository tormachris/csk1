#!/bin/bash

if [[ "$#" -ne "2" ]]; then
	echo "Usage:"
	echo "$0 [.jar file to test] [test data directory]"
	echo "Example $0 KillerSokoban.jar testdata"
	exit 1
fi

BINARY="$1"
TEST_DATA="$2"

if [[ ! -f "$1" ]]; then
	echo ".jar not exists"
	exit 1
fi

if [[ ! -d "$2" ]]; then
	echo "test case directory not exists"
	exit 1
fi

success="0"
total="0"

for t in $TEST_DATA/*.testcase; do

	echo -e "\n-----------------------"
	echo "Running: $t..."

	EXPECTED="$t.expected"

	if [[ -f "$EXPECTED" ]]; then

#		$BINARY < "$t" | diff - "$EXPECTED" # ha nem java-t tesztelunk
		java -jar "$BINARY" < "$t" | diff - "$EXPECTED" # actual test

		result=("${PIPESTATUS[@]}")

		if [[ "${result[0]}" -ne "0" ]]; then
			echo "PROGRAM CRASHED!"
			echo "TEST FAILED!"
			continue
		fi

		if [[ "${result[1]}" -ne "0" ]]; then

			echo "TEST FAILED!"
		else
			echo "TEST SUCCESSFUL!"
			success=$(( $success + 1 ))
		fi


	else
		echo "WARNING: No expected output specified. Running test anyways"

#		$BINARY < "$t" # actual test, ha nem java-t tesztelunk
		java -jar "$BINARY" < "$t" # actual test

	fi

	total=$(( $total + 1 ))
done


echo -e  "\n-----------------------"
echo "Done!"
echo "Total tests run: $total"
echo "Successful tests: $success"
echo "Failed tests $(( $total - $success ))"
[[ "$total" -eq "$success" ]] && echo "Complete success!!"
echo "-----------------------"
